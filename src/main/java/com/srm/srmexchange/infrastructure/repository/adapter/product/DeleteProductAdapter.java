package com.srm.srmexchange.infrastructure.repository.adapter.product;

import com.srm.srmexchange.domain.domain.ProductEntity;
import com.srm.srmexchange.domain.port.out.product.DeleteProductPort;
import com.srm.srmexchange.infrastructure.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeleteProductAdapter implements DeleteProductPort {

    private final ProductRepository repository;

    @Override
    public void execute(ProductEntity entity) {
        log.info("Deleting entity: {}", entity);
        repository.delete(entity);
        log.debug("Entity deleted");
    }

}