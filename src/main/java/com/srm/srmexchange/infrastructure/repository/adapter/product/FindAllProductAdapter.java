package com.srm.srmexchange.infrastructure.repository.adapter.product;

import com.srm.srmexchange.domain.domain.ProductEntity;
import com.srm.srmexchange.domain.port.out.product.FindAllProductPort;
import com.srm.srmexchange.infrastructure.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class FindAllProductAdapter implements FindAllProductPort {

    private final ProductRepository repository;

    @Override
    public List<ProductEntity> execute() {
        log.debug("Finding all entities");
        List<ProductEntity> list = repository.findAll();
        log.debug("Found {} entities", list.size());

        return list;
    }

}