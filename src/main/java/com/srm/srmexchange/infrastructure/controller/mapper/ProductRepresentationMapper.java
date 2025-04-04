package com.srm.srmexchange.infrastructure.controller.mapper;

import com.srm.representation.ProductRequestRepresentation;
import com.srm.representation.ProductResponseRepresentation;
import com.srm.srmexchange.application.dto.product.ProductInbound;
import com.srm.srmexchange.application.dto.product.ProductOutbound;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductRepresentationMapper {

    ProductResponseRepresentation toRepresentation(ProductOutbound productOutbound);
    ProductInbound toInbound(ProductRequestRepresentation productRequestRepresentation);

}