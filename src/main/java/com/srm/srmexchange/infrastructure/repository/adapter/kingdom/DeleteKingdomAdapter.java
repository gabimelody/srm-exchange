package com.srm.srmexchange.infrastructure.repository.adapter.kingdom;

import com.srm.srmexchange.domain.domain.KingdomEntity;
import com.srm.srmexchange.domain.port.out.kingdom.DeleteKingdomPort;
import com.srm.srmexchange.infrastructure.repository.KingdomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeleteKingdomAdapter implements DeleteKingdomPort {

    private final KingdomRepository repository;

    @Override
    public void execute(KingdomEntity entity) {
        log.info("Deleting entity: {}", entity);
        repository.delete(entity);
        log.debug("Entity deleted");
    }

}