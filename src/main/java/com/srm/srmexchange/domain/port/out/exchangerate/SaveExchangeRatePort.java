package com.srm.srmexchange.domain.port.out.exchangerate;

import com.srm.srmexchange.domain.domain.ExchangeRateEntity;

public interface SaveExchangeRatePort {

    ExchangeRateEntity execute(ExchangeRateEntity entity);

}