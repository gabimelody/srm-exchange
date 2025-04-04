package com.srm.srmexchange.application.usecase.exchangerate;

import com.srm.srmexchange.application.dto.exchangerate.ExchangeRateInbound;
import com.srm.srmexchange.application.dto.exchangerate.ExchangeRateOutbound;
import com.srm.srmexchange.application.mapper.ExchangeRateEntityMapper;
import com.srm.srmexchange.domain.domain.CoinEntity;
import com.srm.srmexchange.domain.domain.ExchangeRateEntity;
import com.srm.srmexchange.domain.exception.CoinNotFoundException;
import com.srm.srmexchange.domain.exception.CoinsAreTheSameException;
import com.srm.srmexchange.domain.exception.ExchangeRateAlreadyExistException;
import com.srm.srmexchange.domain.port.in.exchangerate.CreateExchangeRatePort;
import com.srm.srmexchange.domain.port.out.coin.FindByIdCoinPort;
import com.srm.srmexchange.domain.port.out.exchangerate.FindByCoinsExchangeRatePort;
import com.srm.srmexchange.domain.port.out.exchangerate.SaveExchangeRatePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class CreateExchangeRateUseCase implements CreateExchangeRatePort {

    private final FindByIdCoinPort findByIdCoinPort;
    private final SaveExchangeRatePort saveExchangeRatePort;
    private final ExchangeRateEntityMapper exchangeRateEntityMapper;
    private final FindByCoinsExchangeRatePort findByCoinsExchangeRatePort;

    @Override
    @Transactional
    public ExchangeRateOutbound execute(ExchangeRateInbound inbound) {
        validate(inbound);

        ExchangeRateEntity entity = exchangeRateEntityMapper.toEntity(inbound);
        entity.setCoinTo(findCoinById(inbound.getIdCoinTo()));
        entity.setCoinFrom(findCoinById(inbound.getIdCoinFrom()));
        entity = saveExchangeRatePort.execute(entity);

        log.info("Exchange rate created: {}", entity);
        return exchangeRateEntityMapper.toOutbound(entity);
    }

    private void validate(ExchangeRateInbound inbound) {
        if (inbound.getIdCoinTo().equals(inbound.getIdCoinFrom())) {
            throw new CoinsAreTheSameException();
        }

        if (findByCoinsExchangeRatePort.execute(inbound.getIdCoinTo(), inbound.getIdCoinFrom()).isEmpty()) {
            throw new ExchangeRateAlreadyExistException();
        }
    }

    private CoinEntity findCoinById(UUID idCoinBase) {
        return findByIdCoinPort.execute(idCoinBase)
                .orElseThrow(CoinNotFoundException::new);
    }

}