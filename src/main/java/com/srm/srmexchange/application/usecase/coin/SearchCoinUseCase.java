package com.srm.srmexchange.application.usecase.coin;

import com.srm.srmexchange.application.dto.coin.CoinOutbound;
import com.srm.srmexchange.application.mapper.CoinEntityMapper;
import com.srm.srmexchange.domain.domain.CoinEntity;
import com.srm.srmexchange.domain.port.in.coin.SearchCoinPort;
import com.srm.srmexchange.domain.port.out.coin.FindAllCoinPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class SearchCoinUseCase implements SearchCoinPort {

    private final FindAllCoinPort findAllCoinPort;
    private final CoinEntityMapper coinEntityMapper;

    @Override
    public List<CoinOutbound> execute() {
        log.info("Searching all coins");
        return findAllCoinPort.execute().stream()
                .map(this::mapToOutbound)
                .toList();
    }

    private CoinOutbound mapToOutbound(CoinEntity entity) {
        log.info("Coin found: {}", entity);
        return coinEntityMapper.toOutbound(entity);
    }

}