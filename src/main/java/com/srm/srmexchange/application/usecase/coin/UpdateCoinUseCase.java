package com.srm.srmexchange.application.usecase.coin;

import com.srm.srmexchange.application.dto.coin.CoinInbound;
import com.srm.srmexchange.application.dto.coin.CoinOutbound;
import com.srm.srmexchange.application.mapper.CoinEntityMapper;
import com.srm.srmexchange.domain.domain.CoinEntity;
import com.srm.srmexchange.domain.port.in.coin.UpdateCoinPort;
import com.srm.srmexchange.domain.port.out.coin.FindByIdCoinPort;
import com.srm.srmexchange.domain.port.out.coin.SaveCoinPort;
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
public class UpdateCoinUseCase implements UpdateCoinPort {

    private final SaveCoinPort saveCoinPort;
    private final FindByIdCoinPort findByIdCoinPort;
    private final CoinEntityMapper coinEntityMapper;

    @Override
    @Transactional
    public CoinOutbound execute(UUID id, CoinInbound inbound) {
        log.info("Updating coin by id: {}", id);
        return findByIdCoinPort.execute(id)
                .map(entity -> convert(entity, inbound))
                .map(saveCoinPort::execute)
                .map(coinEntityMapper::toOutbound)
                .orElse(null);
    }

    private CoinEntity convert(CoinEntity entity, CoinInbound inbound) {
        log.info("Previous coin: {}", entity);
        entity.setName(inbound.getName());
        return entity;
    }

}