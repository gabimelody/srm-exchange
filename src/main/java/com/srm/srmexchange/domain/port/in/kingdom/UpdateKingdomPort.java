package com.srm.srmexchange.domain.port.in.kingdom;

import com.srm.srmexchange.application.dto.kingdom.KingdomInbound;
import com.srm.srmexchange.application.dto.kingdom.KingdomOutbound;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public interface UpdateKingdomPort {

    KingdomOutbound execute(
            @NotNull(message = "Kingdom id is required") UUID uuid,
            @Valid KingdomInbound inbound
    );

}