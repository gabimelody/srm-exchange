package com.srm.srmexchange.domain.port.in.exchangerate;

import com.srm.srmexchange.application.dto.exchangerate.ExchangeRateOutbound;

import java.util.UUID;

public interface SearchExchangeRateByIdPort {

    ExchangeRateOutbound execute(UUID id);

}