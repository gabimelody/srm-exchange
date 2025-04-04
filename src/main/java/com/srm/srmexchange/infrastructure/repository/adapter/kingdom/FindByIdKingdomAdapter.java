package com.srm.srmexchange.infrastructure.repository.adapter.kingdom;

import com.srm.srmexchange.domain.domain.KingdomEntity;
import com.srm.srmexchange.domain.port.out.kingdom.FindByIdKingdomPort;
import com.srm.srmexchange.infrastructure.repository.KingdomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class FindByIdKingdomAdapter implements FindByIdKingdomPort {

    private final KingdomRepository repository;

    @Override
    public Optional<KingdomEntity> execute(UUID id) {
        log.debug("Finding entity by id: {}", id);
        Optional<KingdomEntity> optional = repository.findById(id);
        log.debug("Entity found: {}", optional.isPresent());

        return optional;
    }

}