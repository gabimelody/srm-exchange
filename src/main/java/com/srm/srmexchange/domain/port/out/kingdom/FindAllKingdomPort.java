package com.srm.srmexchange.domain.port.out.kingdom;

import com.srm.srmexchange.domain.domain.KingdomEntity;

import java.util.List;

public interface FindAllKingdomPort {

    List<KingdomEntity> execute();

}