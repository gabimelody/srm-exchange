package com.srm.srmexchange.infrastructure.repository.adapter.product;

import com.srm.srmexchange.domain.domain.ProductEntity;
import com.srm.srmexchange.domain.port.out.product.FindByIdProductPort;
import com.srm.srmexchange.infrastructure.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FindByIdProductAdapter implements FindByIdProductPort {

    private final ProductRepository productRepository;

    @Override
    public Optional<ProductEntity> execute(UUID id) {
        return productRepository.findById(id);
    }

}
