package com.srm.srmexchange.domain.port.out.exchangerate;

import com.srm.srmexchange.domain.domain.ExchangeRateEntity;

public interface DeleteExchangeRatePort {

    void execute(ExchangeRateEntity entity);

}