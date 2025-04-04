package com.srm.srmexchange.infrastructure.controller.mapper;

import com.srm.representation.CoinRequestRepresentation;
import com.srm.representation.CoinResponseRepresentation;
import com.srm.srmexchange.application.dto.coin.CoinInbound;
import com.srm.srmexchange.application.dto.coin.CoinOutbound;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CoinRepresentationMapper {

    CoinResponseRepresentation toRepresentation(CoinOutbound coinOutbound);
    CoinInbound toInbound(CoinRequestRepresentation coinRequestRepresentation);

}