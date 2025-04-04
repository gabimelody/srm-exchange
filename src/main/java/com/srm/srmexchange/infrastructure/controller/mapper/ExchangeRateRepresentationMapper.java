package com.srm.srmexchange.infrastructure.controller.mapper;

import com.srm.representation.ExchangeRateRequestRepresentation;
import com.srm.representation.ExchangeRateResponseRepresentation;
import com.srm.srmexchange.application.dto.exchangerate.ExchangeRateInbound;
import com.srm.srmexchange.application.dto.exchangerate.ExchangeRateOutbound;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        CoinRepresentationMapper.class})
public interface ExchangeRateRepresentationMapper {

    ExchangeRateInbound toInbound(ExchangeRateRequestRepresentation representation);
    ExchangeRateResponseRepresentation toRepresentation(ExchangeRateOutbound outbound);

}