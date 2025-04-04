package com.srm.srmexchange.infrastructure.repository.adapter.coin;

import com.srm.srmexchange.domain.domain.CoinEntity;
import com.srm.srmexchange.domain.port.out.coin.FindAllCoinPort;
import com.srm.srmexchange.infrastructure.repository.CoinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindAllCoinAdapter implements FindAllCoinPort {

    private final CoinRepository coinRepository;

    @Override
    public List<CoinEntity> execute() {
        return coinRepository.findAll();
    }

}
