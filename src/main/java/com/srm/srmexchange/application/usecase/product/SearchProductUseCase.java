package com.srm.srmexchange.application.usecase.product;

import com.srm.srmexchange.application.dto.product.ProductOutbound;
import com.srm.srmexchange.application.mapper.ProductEntityMapper;
import com.srm.srmexchange.domain.domain.ProductEntity;
import com.srm.srmexchange.domain.port.in.product.SearchProductPort;
import com.srm.srmexchange.domain.port.out.product.FindAllProductPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class SearchProductUseCase implements SearchProductPort {

    private final FindAllProductPort findAllProductPort;
    private final ProductEntityMapper productEntityMapper;

    @Override
    public List<ProductOutbound> execute() {
        log.info("Searching all products");
        return findAllProductPort.execute().stream()
                .map(this::mapToOutbound)
                .toList();
    }

    private ProductOutbound mapToOutbound(ProductEntity entity) {
        log.info("Product found: {}", entity);
        return productEntityMapper.toOutbound(entity);
    }

}