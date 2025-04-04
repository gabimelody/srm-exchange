package com.srm.srmexchange.domain.port.in.kingdom;

import com.srm.srmexchange.application.dto.kingdom.KingdomOutbound;

import java.util.List;

public interface SearchKingdomPort {

    List<KingdomOutbound> execute();

}