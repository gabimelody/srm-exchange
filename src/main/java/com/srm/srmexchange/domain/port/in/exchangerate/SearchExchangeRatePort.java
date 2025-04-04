package com.srm.srmexchange.domain.port.in.exchangerate;

import com.srm.srmexchange.application.dto.exchangerate.ExchangeRateOutbound;

import java.util.List;

public interface SearchExchangeRatePort {

    List<ExchangeRateOutbound> execute();

}