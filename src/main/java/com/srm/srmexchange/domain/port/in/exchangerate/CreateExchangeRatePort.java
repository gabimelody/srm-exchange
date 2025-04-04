package com.srm.srmexchange.domain.port.in.exchangerate;

import com.srm.srmexchange.application.dto.exchangerate.ExchangeRateInbound;
import com.srm.srmexchange.application.dto.exchangerate.ExchangeRateOutbound;
import jakarta.validation.Valid;

public interface CreateExchangeRatePort {

    ExchangeRateOutbound execute(@Valid ExchangeRateInbound inbound);

}