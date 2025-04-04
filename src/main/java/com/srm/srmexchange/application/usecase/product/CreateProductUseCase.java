package com.srm.srmexchange.application.usecase.product;

import com.srm.srmexchange.application.dto.product.ProductInbound;
import com.srm.srmexchange.application.dto.product.ProductOutbound;
import com.srm.srmexchange.application.mapper.ProductEntityMapper;
import com.srm.srmexchange.domain.domain.ProductEntity;
import com.srm.srmexchange.domain.port.in.product.CreateProductPort;
import com.srm.srmexchange.domain.port.out.product.SaveProductPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class CreateProductUseCase implements CreateProductPort {

    private final SaveProductPort saveProductPort;
    private final ProductEntityMapper productEntityMapper;

    @Override
    @Transactional
    public ProductOutbound execute(ProductInbound inbound) {
        ProductEntity entity = productEntityMapper.toEntity(inbound);
        entity = saveProductPort.execute(entity);

        log.info("Product created: {}", entity);
        return productEntityMapper.toOutbound(entity);
    }

}