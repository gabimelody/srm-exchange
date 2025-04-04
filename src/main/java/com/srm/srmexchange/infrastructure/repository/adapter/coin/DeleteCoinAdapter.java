package com.srm.srmexchange.infrastructure.repository.adapter.coin;

import com.srm.srmexchange.domain.domain.CoinEntity;
import com.srm.srmexchange.domain.port.out.coin.DeleteCoinPort;
import com.srm.srmexchange.infrastructure.repository.CoinRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeleteCoinAdapter implements DeleteCoinPort {

    private final CoinRepository coinRepository;

    @Override
    public void execute(CoinEntity entity) {
        log.info("Deleting coin: {}", entity);
        coinRepository.delete(entity);
    }

}
