package com.srm.srmexchange.application.mapper;

import com.srm.srmexchange.application.dto.product.ProductInbound;
import com.srm.srmexchange.application.dto.product.ProductOutbound;
import com.srm.srmexchange.domain.domain.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        KingdomEntityMapper.class,
        CoinEntityMapper.class})
public interface ProductEntityMapper {

    ProductEntity toEntity(ProductInbound productInbound);
    ProductOutbound toOutbound(ProductEntity productEntity);

}