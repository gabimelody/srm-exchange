package com.srm.srmexchange.infrastructure.repository.adapter.coin;

import com.srm.srmexchange.domain.domain.CoinEntity;
import com.srm.srmexchange.domain.port.out.coin.FindAllCoinPort;
import com.srm.srmexchange.infrastructure.repository.CoinRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class FindAllCoinAdapter implements FindAllCoinPort {

    private final CoinRepository repository;

    @Override
    public List<CoinEntity> execute() {
        log.debug("Finding all entities");
        List<CoinEntity> list = repository.findAll();
        log.debug("Found {} entities", list.size());

        return list;
    }

}