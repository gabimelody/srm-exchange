package com.srm.srmexchange.application.usecase.product;

import com.srm.srmexchange.application.dto.product.ProductInbound;
import com.srm.srmexchange.application.dto.product.ProductOutbound;
import com.srm.srmexchange.application.mapper.ProductEntityMapper;
import com.srm.srmexchange.domain.domain.ProductEntity;
import com.srm.srmexchange.domain.port.in.product.UpdateProductPort;
import com.srm.srmexchange.domain.port.out.product.FindByIdProductPort;
import com.srm.srmexchange.domain.port.out.product.SaveProductPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class UpdateProductUseCase implements UpdateProductPort {

    private final SaveProductPort saveProductPort;
    private final FindByIdProductPort findByIdProductPort;
    private final ProductEntityMapper productEntityMapper;

    @Override
    @Transactional
    public ProductOutbound execute(UUID id, ProductInbound inbound) {
        log.info("Updating product by id: {}", id);
        return findByIdProductPort.execute(id)
                .map(entity -> convert(entity, inbound))
                .map(saveProductPort::execute)
                .map(productEntityMapper::toOutbound)
                .orElse(null);
    }

    private ProductEntity convert(ProductEntity productEntity, ProductInbound inbound) {
        log.info("Previous product: {}", productEntity);
        productEntity.setName(inbound.getName());
        return productEntity;
    }

}