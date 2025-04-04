package com.srm.srmexchange.domain.port.out.product;

import com.srm.srmexchange.domain.domain.ProductEntity;

import java.util.List;

public interface FindAllProductPort {

    List<ProductEntity> execute();

}