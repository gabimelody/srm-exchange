package com.srm.srmexchange.domain.port.in.exchangerate;

import com.srm.srmexchange.application.dto.exchangerate.ExchangeRateInbound;
import com.srm.srmexchange.application.dto.exchangerate.ExchangeRateOutbound;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public interface UpdateExchangeRatePort {

    ExchangeRateOutbound execute(
            @NotNull(message = "Exchange rate id is required") UUID uuid,
            @Valid ExchangeRateInbound inbound
    );

}