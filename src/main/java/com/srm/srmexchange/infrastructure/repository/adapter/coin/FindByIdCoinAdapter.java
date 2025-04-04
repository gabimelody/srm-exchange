package com.srm.srmexchange.infrastructure.repository.adapter.coin;

import com.srm.srmexchange.domain.domain.CoinEntity;
import com.srm.srmexchange.domain.port.out.coin.FindByIdCoinPort;
import com.srm.srmexchange.infrastructure.repository.CoinRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class FindByIdCoinAdapter implements FindByIdCoinPort {

    private final CoinRepository repository;

    @Override
    public Optional<CoinEntity> execute(UUID id) {
        log.debug("Finding entity by id: {}", id);
        Optional<CoinEntity> optional = repository.findById(id);
        log.debug("Entity found: {}", optional.isPresent());

        return optional;
    }

}