package com.srm.srmexchange.domain.port.in.product;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public interface RemoveProductPort {

    boolean execute(@NotNull(message = "Product id is required") UUID uuid);

}