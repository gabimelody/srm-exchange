package com.srm.srmexchange.application.usecase.product;

import com.srm.srmexchange.application.dto.product.ProductOutbound;
import com.srm.srmexchange.application.mapper.ProductEntityMapper;
import com.srm.srmexchange.domain.domain.ProductEntity;
import com.srm.srmexchange.domain.port.in.product.SearchProductByIdPort;
import com.srm.srmexchange.domain.port.out.product.FindByIdProductPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class SearchProductByIdUseCase implements SearchProductByIdPort {

    private final ProductEntityMapper productEntityMapper;
    private final FindByIdProductPort findByIdProductPort;

    @Override
    public ProductOutbound execute(UUID id) {
        log.info("Searching product by id: {}", id);
        return findByIdProductPort.execute(id)
                .map(this::mapToOutbound)
                .orElse(null);
    }

    private ProductOutbound mapToOutbound(ProductEntity entity) {
        log.info("Product found: {}", entity);
        return productEntityMapper.toOutbound(entity);
    }

}