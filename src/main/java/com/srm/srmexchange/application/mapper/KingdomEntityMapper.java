package com.srm.srmexchange.application.mapper;

import com.srm.srmexchange.application.dto.kingdom.KingdomInbound;
import com.srm.srmexchange.application.dto.kingdom.KingdomOutbound;
import com.srm.srmexchange.domain.domain.KingdomEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface KingdomEntityMapper {

    KingdomEntity toEntity(KingdomInbound kingdomInbound);
    KingdomOutbound toOutbound(KingdomEntity kingdomEntity);

}