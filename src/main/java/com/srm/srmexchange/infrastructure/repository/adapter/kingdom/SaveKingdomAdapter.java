package com.srm.srmexchange.infrastructure.repository.adapter.kingdom;

import com.srm.srmexchange.domain.domain.KingdomEntity;
import com.srm.srmexchange.domain.port.out.kingdom.SaveKingdomPort;
import com.srm.srmexchange.infrastructure.repository.KingdomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SaveKingdomAdapter implements SaveKingdomPort {

    private final KingdomRepository kingdomRepository;

    @Override
    public KingdomEntity execute(KingdomEntity entity) {
        log.info("Creating kingdom: {}", entity);
        return kingdomRepository.save(entity);
    }

}
