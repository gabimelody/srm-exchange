package com.srm.srmexchange.infrastructure.repository.adapter.coin;

import com.srm.srmexchange.domain.domain.CoinEntity;
import com.srm.srmexchange.domain.port.out.coin.FindByIdCoinPort;
import com.srm.srmexchange.infrastructure.repository.CoinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FindByIdCoinAdapter implements FindByIdCoinPort {

    private final CoinRepository coinRepository;

    @Override
    public Optional<CoinEntity> execute(UUID id) {
        return coinRepository.findById(id);
    }

}
