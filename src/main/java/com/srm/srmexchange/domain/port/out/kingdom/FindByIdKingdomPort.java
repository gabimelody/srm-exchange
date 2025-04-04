package com.srm.srmexchange.domain.port.out.kingdom;

import com.srm.srmexchange.domain.domain.KingdomEntity;

import java.util.Optional;
import java.util.UUID;

public interface FindByIdKingdomPort {

    Optional<KingdomEntity> execute(UUID id);

}