package com.srm.srmexchange.domain.port.in.coin;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public interface RemoveCoinPort {

    boolean execute(@NotNull(message = "Coin id is required") UUID uuid);

}