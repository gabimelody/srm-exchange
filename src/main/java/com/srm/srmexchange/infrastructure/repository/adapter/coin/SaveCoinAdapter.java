package com.srm.srmexchange.infrastructure.repository.adapter.coin;

import com.srm.srmexchange.domain.domain.CoinEntity;
import com.srm.srmexchange.domain.port.out.coin.SaveCoinPort;
import com.srm.srmexchange.infrastructure.repository.CoinRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SaveCoinAdapter implements SaveCoinPort {

    private final CoinRepository repository;

    @Override
    public CoinEntity execute(CoinEntity entity) {
        log.info("Creating entity: {}", entity);
        entity = repository.save(entity);
        log.info("Entity created: {}", entity);

        return entity;
    }

}