package com.srm.srmexchange.application.usecase.product;

import com.srm.srmexchange.domain.domain.ProductEntity;
import com.srm.srmexchange.domain.port.in.product.RemoveProductPort;
import com.srm.srmexchange.domain.port.out.product.DeleteProductPort;
import com.srm.srmexchange.domain.port.out.product.FindByIdProductPort;
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
public class RemoveProductUseCase implements RemoveProductPort {

    private final DeleteProductPort deleteProductPort;
    private final FindByIdProductPort findByIdProductPort;

    @Override
    @Transactional
    public boolean execute(UUID id) {
        log.info("Removing product by id: {}", id);
        return findByIdProductPort.execute(id)
                .map(this::remove)
                .orElse(false);
    }

    private boolean remove(ProductEntity productEntity) {
        deleteProductPort.execute(productEntity);
        return true;
    }

}