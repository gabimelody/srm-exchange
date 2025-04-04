package com.srm.srmexchange.domain.port.in.kingdom;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public interface RemoveKingdomPort {

    boolean execute(@NotNull(message = "Kingdom id is required") UUID uuid);

}