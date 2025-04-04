package com.srm.srmexchange.infrastructure.repository.adapter.product;

import com.srm.srmexchange.domain.domain.ProductEntity;
import com.srm.srmexchange.domain.port.out.product.SaveProductPort;
import com.srm.srmexchange.infrastructure.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SaveProductAdapter implements SaveProductPort {

    private final ProductRepository repository;

    @Override
    public ProductEntity execute(ProductEntity entity) {
        log.info("Creating entity: {}", entity);
        entity = repository.save(entity);
        log.info("Entity created: {}", entity);

        return entity;
    }

}