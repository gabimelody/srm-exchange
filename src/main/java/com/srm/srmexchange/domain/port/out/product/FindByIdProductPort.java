package com.srm.srmexchange.domain.port.out.product;

import com.srm.srmexchange.domain.domain.ProductEntity;

import java.util.Optional;
import java.util.UUID;

public interface FindByIdProductPort {

    Optional<ProductEntity> execute(UUID id);

}