package com.srm.srmexchange.domain.port.in.product;

import com.srm.srmexchange.application.dto.product.ProductInbound;
import com.srm.srmexchange.application.dto.product.ProductOutbound;
import jakarta.validation.Valid;

public interface CreateProductPort {

    ProductOutbound execute(@Valid ProductInbound inbound);

}