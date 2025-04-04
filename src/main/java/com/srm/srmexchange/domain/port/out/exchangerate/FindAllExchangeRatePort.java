package com.srm.srmexchange.domain.port.out.exchangerate;

import com.srm.srmexchange.domain.domain.ExchangeRateEntity;

import java.util.List;

public interface FindAllExchangeRatePort {

    List<ExchangeRateEntity> execute();

}