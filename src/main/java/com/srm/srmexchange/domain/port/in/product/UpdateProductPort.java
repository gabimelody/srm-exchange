package com.srm.srmexchange.domain.port.in.product;

import com.srm.srmexchange.application.dto.product.ProductInbound;
import com.srm.srmexchange.application.dto.product.ProductOutbound;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public interface UpdateProductPort {

    ProductOutbound execute(
            @NotNull(message = "Product id is required") UUID uuid,
            @Valid ProductInbound inbound
    );

}