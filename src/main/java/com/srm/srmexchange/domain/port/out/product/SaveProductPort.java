package com.srm.srmexchange.domain.port.out.product;

import com.srm.srmexchange.domain.domain.ProductEntity;

public interface SaveProductPort {

    ProductEntity execute(ProductEntity entity);

}