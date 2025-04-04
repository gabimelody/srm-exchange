package com.srm.srmexchange.infrastructure.controller.mapper;

import com.srm.representation.KingdomRequestRepresentation;
import com.srm.representation.KingdomResponseRepresentation;
import com.srm.srmexchange.application.dto.kingdom.KingdomInbound;
import com.srm.srmexchange.application.dto.kingdom.KingdomOutbound;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface KingdomRepresentationMapper {

    KingdomResponseRepresentation toRepresentation(KingdomOutbound kingdomOutbound);
    KingdomInbound toInbound(KingdomRequestRepresentation kingdomRequestRepresentation);

}