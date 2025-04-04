package com.srm.srmexchange.domain.port.in.kingdom;

import com.srm.srmexchange.application.dto.kingdom.KingdomOutbound;

import java.util.UUID;

public interface SearchKingdomByIdPort {

    KingdomOutbound execute(UUID id);

}