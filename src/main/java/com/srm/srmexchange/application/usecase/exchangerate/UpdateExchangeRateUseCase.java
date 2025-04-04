package com.srm.srmexchange.application.usecase.exchangerate;

import com.srm.srmexchange.application.dto.exchangerate.ExchangeRateInbound;
import com.srm.srmexchange.application.dto.exchangerate.ExchangeRateOutbound;
import com.srm.srmexchange.application.mapper.ExchangeRateEntityMapper;
import com.srm.srmexchange.domain.domain.CoinEntity;
import com.srm.srmexchange.domain.domain.ExchangeRateEntity;
import com.srm.srmexchange.domain.exception.CoinNotFoundException;
import com.srm.srmexchange.domain.exception.CoinsAreTheSameException;
import com.srm.srmexchange.domain.exception.ExchangeRateAlreadyExistException;
import com.srm.srmexchange.domain.port.in.exchangerate.UpdateExchangeRatePort;
import com.srm.srmexchange.domain.port.out.coin.FindByIdCoinPort;
import com.srm.srmexchange.domain.port.out.exchangerate.FindByCoinsExchangeRatePort;
import com.srm.srmexchange.domain.port.out.exchangerate.FindByIdExchangeRatePort;
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
public class UpdateExchangeRateUseCase implements UpdateExchangeRatePort {

    private final FindByIdCoinPort findByIdCoinPort;
    private final SaveExchangeRatePort saveExchangeRatePort;
    private final FindByIdExchangeRatePort findByIdExchangeRatePort;
    private final ExchangeRateEntityMapper exchangeRateEntityMapper;
    private final FindByCoinsExchangeRatePort findByCoinsExchangeRatePort;

    @Override
    @Transactional
    public ExchangeRateOutbound execute(UUID id, ExchangeRateInbound inbound) {
        log.info("Updating exchange rate by id: {}", id);
        validate(id, inbound);

        return findByIdExchangeRatePort.execute(id)
                .map(entity -> convert(entity, inbound))
                .map(saveExchangeRatePort::execute)
                .map(exchangeRateEntityMapper::toOutbound)
                .orElse(null);
    }

    private void validate(UUID id, ExchangeRateInbound inbound) {
        if (inbound.getIdCoinTo().equals(inbound.getIdCoinFrom())) {
            throw new CoinsAreTheSameException();
        }

        if (isDuplicateExchangeRate(id, inbound)) {
            throw new ExchangeRateAlreadyExistException();
        }
    }

    private boolean isDuplicateExchangeRate(UUID id, ExchangeRateInbound inbound) {
        return findByCoinsExchangeRatePort.execute(inbound.getIdCoinTo(), inbound.getIdCoinFrom())
                .map(entity -> !entity.getId().equals(id))
                .orElse(false);
    }

    private ExchangeRateEntity convert(ExchangeRateEntity entity, ExchangeRateInbound inbound) {
        log.info("Previous exchange rate: {}", entity);
        entity.setVlRate(inbound.getVlRate());
        entity.setCoinTo(findCoinById(inbound.getIdCoinTo()));
        entity.setCoinFrom(findCoinById(inbound.getIdCoinFrom()));

        return entity;
    }

    private CoinEntity findCoinById(UUID idCoinBase) {
        return findByIdCoinPort.execute(idCoinBase)
                .orElseThrow(CoinNotFoundException::new);
    }

}