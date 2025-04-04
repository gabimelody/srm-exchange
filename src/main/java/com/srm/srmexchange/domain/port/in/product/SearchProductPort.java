package com.srm.srmexchange.domain.port.in.product;

import com.srm.srmexchange.application.dto.product.ProductOutbound;

import java.util.List;

public interface SearchProductPort {

    List<ProductOutbound> execute();

}