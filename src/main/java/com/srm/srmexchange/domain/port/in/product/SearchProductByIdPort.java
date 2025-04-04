package com.srm.srmexchange.domain.port.in.product;

import com.srm.srmexchange.application.dto.product.ProductOutbound;

import java.util.UUID;

public interface SearchProductByIdPort {

    ProductOutbound execute(UUID id);

}