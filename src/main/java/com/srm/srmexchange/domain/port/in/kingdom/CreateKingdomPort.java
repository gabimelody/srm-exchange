package com.srm.srmexchange.domain.port.in.kingdom;

import com.srm.srmexchange.application.dto.kingdom.KingdomInbound;
import com.srm.srmexchange.application.dto.kingdom.KingdomOutbound;
import jakarta.validation.Valid;

public interface CreateKingdomPort {

    KingdomOutbound execute(@Valid KingdomInbound inbound);

}