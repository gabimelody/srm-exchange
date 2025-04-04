package com.srm.srmexchange.application.usecase.coin;

import com.srm.srmexchange.application.dto.coin.CoinInbound;
import com.srm.srmexchange.application.dto.coin.CoinOutbound;
import com.srm.srmexchange.application.mapper.CoinEntityMapper;
import com.srm.srmexchange.domain.domain.CoinEntity;
import com.srm.srmexchange.domain.port.in.coin.CreateCoinPort;
import com.srm.srmexchange.domain.port.out.coin.SaveCoinPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class CreateCoinUseCase implements CreateCoinPort {

    private final SaveCoinPort saveCoinPort;
    private final CoinEntityMapper coinEntityMapper;

    @Override
    @Transactional
    public CoinOutbound execute(CoinInbound inbound) {
        CoinEntity entity = coinEntityMapper.toEntity(inbound);
        entity = saveCoinPort.execute(entity);

        log.info("Coin created: {}", entity);
        return coinEntityMapper.toOutbound(entity);
    }

}