package com.srm.srmexchange.infrastructure.repository.adapter.kingdom;

import com.srm.srmexchange.domain.domain.KingdomEntity;
import com.srm.srmexchange.domain.port.out.kingdom.FindAllKingdomPort;
import com.srm.srmexchange.infrastructure.repository.KingdomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindAllKingdomAdapter implements FindAllKingdomPort {

    private final KingdomRepository kingdomRepository;

    @Override
    public List<KingdomEntity> execute() {
        return kingdomRepository.findAll();
    }

}
