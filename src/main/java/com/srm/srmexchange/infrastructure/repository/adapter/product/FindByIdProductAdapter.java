package com.srm.srmexchange.infrastructure.repository.adapter.product;

import com.srm.srmexchange.domain.domain.ProductEntity;
import com.srm.srmexchange.domain.port.out.product.FindByIdProductPort;
import com.srm.srmexchange.infrastructure.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class FindByIdProductAdapter implements FindByIdProductPort {

    private final ProductRepository repository;

    @Override
    public Optional<ProductEntity> execute(UUID id) {
        log.debug("Finding entity by id: {}", id);
        Optional<ProductEntity> optional = repository.findById(id);
        log.debug("Entity found: {}", optional.isPresent());

        return optional;
    }

}