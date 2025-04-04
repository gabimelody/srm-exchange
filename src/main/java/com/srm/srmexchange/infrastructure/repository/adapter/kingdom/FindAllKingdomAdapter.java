package com.srm.srmexchange.infrastructure.repository.adapter.kingdom;

import com.srm.srmexchange.domain.domain.KingdomEntity;
import com.srm.srmexchange.domain.port.out.kingdom.FindAllKingdomPort;
import com.srm.srmexchange.infrastructure.repository.KingdomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class FindAllKingdomAdapter implements FindAllKingdomPort {

    private final KingdomRepository repository;

    @Override
    public List<KingdomEntity> execute() {
        log.debug("Finding all entities");
        List<KingdomEntity> list = repository.findAll();
        log.debug("Found {} entities", list.size());

        return list;
    }

}