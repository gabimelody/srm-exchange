package com.srm.srmexchange.domain.port.out.kingdom;

import com.srm.srmexchange.domain.domain.KingdomEntity;

public interface SaveKingdomPort {

    KingdomEntity execute(KingdomEntity entity);

}