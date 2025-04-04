package com.srm.srmexchange.infrastructure.repository.adapter.kingdom;

import com.srm.srmexchange.domain.domain.KingdomEntity;
import com.srm.srmexchange.domain.port.out.kingdom.FindByIdKingdomPort;
import com.srm.srmexchange.infrastructure.repository.KingdomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FindByIdKingdomAdapter implements FindByIdKingdomPort {

    private final KingdomRepository kingdomRepository;

    @Override
    public Optional<KingdomEntity> execute(UUID id) {
        return kingdomRepository.findById(id);
    }

}
