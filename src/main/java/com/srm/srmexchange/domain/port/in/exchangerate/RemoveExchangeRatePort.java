package com.srm.srmexchange.domain.port.in.exchangerate;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public interface RemoveExchangeRatePort {

    boolean execute(@NotNull(message = "Exchange rate id is required") UUID uuid);

}