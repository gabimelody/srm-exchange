package com.srm.srmexchange.application.usecase.coin;

import com.srm.srmexchange.application.dto.coin.CoinOutbound;
import com.srm.srmexchange.application.mapper.CoinEntityMapper;
import com.srm.srmexchange.domain.domain.CoinEntity;
import com.srm.srmexchange.domain.port.in.coin.SearchCoinByIdPort;
import com.srm.srmexchange.domain.port.out.coin.FindByIdCoinPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class SearchCoinByIdUseCase implements SearchCoinByIdPort {

    private final CoinEntityMapper coinEntityMapper;
    private final FindByIdCoinPort findByIdCoinPort;

    @Override
    public CoinOutbound execute(UUID id) {
        log.info("Searching coin by id: {}", id);
        return findByIdCoinPort.execute(id)
                .map(this::mapToOutbound)
                .orElse(null);
    }

    private CoinOutbound mapToOutbound(CoinEntity entity) {
        log.info("Coin found: {}", entity);
        return coinEntityMapper.toOutbound(entity);
    }

}