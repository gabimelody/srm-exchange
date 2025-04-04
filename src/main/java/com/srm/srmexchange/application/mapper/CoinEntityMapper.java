package com.srm.srmexchange.application.mapper;

import com.srm.srmexchange.application.dto.coin.CoinInbound;
import com.srm.srmexchange.application.dto.coin.CoinOutbound;
import com.srm.srmexchange.domain.domain.CoinEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CoinEntityMapper {

    CoinEntity toEntity(CoinInbound coinInbound);
    CoinOutbound toOutbound(CoinEntity coinEntity);

}