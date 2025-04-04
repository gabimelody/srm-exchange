package com.srm.srmexchange.domain.port.out.coin;

import com.srm.srmexchange.domain.domain.CoinEntity;

import java.util.Optional;
import java.util.UUID;

public interface FindByIdCoinPort {

    Optional<CoinEntity> execute(UUID id);

}