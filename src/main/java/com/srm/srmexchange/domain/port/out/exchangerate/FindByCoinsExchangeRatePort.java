package com.srm.srmexchange.domain.port.out.exchangerate;

import com.srm.srmexchange.domain.domain.ExchangeRateEntity;

import java.util.Optional;
import java.util.UUID;

public interface FindByCoinsExchangeRatePort {

    Optional<ExchangeRateEntity> execute(UUID idCoinFirst, UUID idCoinSecond);

}