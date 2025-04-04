package com.srm.srmexchange.application.usecase.kingdom;

import com.srm.srmexchange.application.dto.kingdom.KingdomOutbound;
import com.srm.srmexchange.application.mapper.KingdomEntityMapper;
import com.srm.srmexchange.domain.domain.KingdomEntity;
import com.srm.srmexchange.domain.port.in.kingdom.SearchKingdomByIdPort;
import com.srm.srmexchange.domain.port.out.kingdom.FindByIdKingdomPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class SearchKingdomByIdUseCase implements SearchKingdomByIdPort {

    private final KingdomEntityMapper kingdomEntityMapper;
    private final FindByIdKingdomPort findByIdKingdomPort;

    @Override
    public KingdomOutbound execute(UUID id) {
        log.info("Searching kingdom by id: {}", id);
        return findByIdKingdomPort.execute(id)
                .map(this::mapToOutbound)
                .orElse(null);
    }

    private KingdomOutbound mapToOutbound(KingdomEntity entity) {
        log.info("Kingdom found: {}", entity);
        return kingdomEntityMapper.toOutbound(entity);
    }

}