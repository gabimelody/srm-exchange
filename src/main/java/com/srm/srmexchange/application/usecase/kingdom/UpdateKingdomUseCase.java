package com.srm.srmexchange.application.usecase.kingdom;

import com.srm.srmexchange.application.dto.kingdom.KingdomInbound;
import com.srm.srmexchange.application.dto.kingdom.KingdomOutbound;
import com.srm.srmexchange.application.mapper.KingdomEntityMapper;
import com.srm.srmexchange.domain.domain.KingdomEntity;
import com.srm.srmexchange.domain.port.in.kingdom.UpdateKingdomPort;
import com.srm.srmexchange.domain.port.out.kingdom.FindByIdKingdomPort;
import com.srm.srmexchange.domain.port.out.kingdom.SaveKingdomPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class UpdateKingdomUseCase implements UpdateKingdomPort {

    private final SaveKingdomPort saveKingdomPort;
    private final FindByIdKingdomPort findByIdKingdomPort;
    private final KingdomEntityMapper kingdomEntityMapper;

    @Override
    @Transactional
    public KingdomOutbound execute(UUID id, KingdomInbound inbound) {
        log.info("Updating kingdom by id: {}", id);
        return findByIdKingdomPort.execute(id)
                .map(entity -> convert(entity, inbound))
                .map(saveKingdomPort::execute)
                .map(kingdomEntityMapper::toOutbound)
                .orElse(null);
    }

    private KingdomEntity convert(KingdomEntity entity, KingdomInbound inbound) {
        log.info("Previous kingdom: {}", entity);
        entity.setName(inbound.getName());
        return entity;
    }

}