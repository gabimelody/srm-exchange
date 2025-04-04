package com.srm.srmexchange.infrastructure.controller.mapper;

import com.srm.representation.TransactionRequestRepresentation;
import com.srm.representation.TransactionResponseRepresentation;
import com.srm.srmexchange.application.dto.transaction.TransactionInbound;
import com.srm.srmexchange.application.dto.transaction.TransactionOutbound;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        CoinRepresentationMapper.class,
        ProductRepresentationMapper.class})
public interface TransactionRepresentationMapper {

    TransactionInbound toInbound(TransactionRequestRepresentation representation);
    TransactionResponseRepresentation toRepresentation(TransactionOutbound outbound);

}