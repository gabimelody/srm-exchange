package com.srm.srmexchange.infrastructure.controller;

import com.srm.openapi.api.ProductsApi;
import com.srm.representation.ProductRequestRepresentation;
import com.srm.representation.ProductResponseRepresentation;
import com.srm.srmexchange.application.dto.product.ProductInbound;
import com.srm.srmexchange.application.dto.product.ProductOutbound;
import com.srm.srmexchange.domain.exception.ResourceNotFoundException;
import com.srm.srmexchange.domain.port.in.product.*;
import com.srm.srmexchange.infrastructure.controller.mapper.ProductRepresentationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class ProductController implements ProductsApi {

    private final CreateProductPort createProductPort;
    private final RemoveProductPort removeProductPort;
    private final SearchProductPort searchProductPort;
    private final UpdateProductPort updateProductPort;
    private final SearchProductByIdPort searchProductByIdPort;
    private final ProductRepresentationMapper productRepresentationMapper;

    @Override
    public ResponseEntity<ProductResponseRepresentation> createProduct(ProductRequestRepresentation productRequestRepresentation) {
        log.info("Received request to create product: {}", productRequestRepresentation);
        ProductInbound inbound = productRepresentationMapper.toInbound(productRequestRepresentation);
        ProductOutbound outbound = createProductPort.execute(inbound);
        ProductResponseRepresentation representation = productRepresentationMapper.toRepresentation(outbound);

        log.info("Returning response of created product: {}", representation);
        return ResponseEntity.status(HttpStatus.CREATED).body(representation);
    }

    @Override
    public ResponseEntity<List<ProductResponseRepresentation>> searchProducts() {
        log.info("Received request to search all products");
        List<ProductResponseRepresentation> representation = searchProductPort.execute().stream()
                .map(productRepresentationMapper::toRepresentation)
                .toList();

        log.info("Returning {} products founded", representation.size());
        return ResponseEntity.ok(representation);
    }

    @Override
    public ResponseEntity<ProductResponseRepresentation> findProductById(UUID uuid) {
        log.info("Received request to search product by uuid: {}", uuid);
        ProductResponseRepresentation representation = Optional.ofNullable(searchProductByIdPort.execute(uuid))
                .map(productRepresentationMapper::toRepresentation)
                .orElseThrow(ResourceNotFoundException::new);

        log.info("Returning response of searched product: {}", representation);
        return ResponseEntity.ok(representation);
    }

    @Override
    public ResponseEntity<ProductResponseRepresentation> updateProduct(UUID uuid, ProductRequestRepresentation productRequestRepresentation) {
        log.info("Received request to update product: {}", productRequestRepresentation);
        ProductInbound inbound = productRepresentationMapper.toInbound(productRequestRepresentation);
        ProductOutbound outbound = updateProductPort.execute(uuid, inbound);
        ProductResponseRepresentation representation = productRepresentationMapper.toRepresentation(outbound);

        log.info("Returning response of updated product: {}", representation);
        return ResponseEntity.ok(representation);
    }

    @Override
    public ResponseEntity<ProductResponseRepresentation> removeProduct(UUID uuid) {
        log.info("Received request to remove product uuid: {}", uuid);
        if (!removeProductPort.execute(uuid)) {
            throw new ResourceNotFoundException();
        }

        return ResponseEntity.noContent().build();
    }

}