package com.srm.srmexchange.application.mapper;

import com.srm.srmexchange.application.dto.exchangerate.ExchangeRateInbound;
import com.srm.srmexchange.application.dto.exchangerate.ExchangeRateOutbound;
import com.srm.srmexchange.domain.domain.ExchangeRateEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        CoinEntityMapper.class})
public interface ExchangeRateEntityMapper {

    ExchangeRateEntity toEntity(ExchangeRateInbound inbound);
    ExchangeRateOutbound toOutbound(ExchangeRateEntity entity);

}