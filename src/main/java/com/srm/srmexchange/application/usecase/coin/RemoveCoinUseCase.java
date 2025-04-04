package com.srm.srmexchange.application.usecase.coin;

import com.srm.srmexchange.domain.domain.CoinEntity;
import com.srm.srmexchange.domain.port.in.coin.RemoveCoinPort;
import com.srm.srmexchange.domain.port.out.coin.DeleteCoinPort;
import com.srm.srmexchange.domain.port.out.coin.FindByIdCoinPort;
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
public class RemoveCoinUseCase implements RemoveCoinPort {

    private final DeleteCoinPort deleteCoinPort;
    private final FindByIdCoinPort findByIdCoinPort;

    @Override
    @Transactional
    public boolean execute(UUID id) {
        log.info("Removing coin by id: {}", id);
        return findByIdCoinPort.execute(id)
                .map(this::remove)
                .orElse(false);
    }

    private boolean remove(CoinEntity coinEntity) {
        deleteCoinPort.execute(coinEntity);
        return true;
    }

}