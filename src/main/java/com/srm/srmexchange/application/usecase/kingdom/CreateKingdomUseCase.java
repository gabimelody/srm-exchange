package com.srm.srmexchange.application.usecase.kingdom;

import com.srm.srmexchange.application.dto.kingdom.KingdomInbound;
import com.srm.srmexchange.application.dto.kingdom.KingdomOutbound;
import com.srm.srmexchange.application.mapper.KingdomEntityMapper;
import com.srm.srmexchange.domain.domain.KingdomEntity;
import com.srm.srmexchange.domain.port.in.kingdom.CreateKingdomPort;
import com.srm.srmexchange.domain.port.out.kingdom.SaveKingdomPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class CreateKingdomUseCase implements CreateKingdomPort {

    private final SaveKingdomPort saveKingdomPort;
    private final KingdomEntityMapper kingdomEntityMapper;

    @Override
    @Transactional
    public KingdomOutbound execute(KingdomInbound inbound) {
        KingdomEntity entity = kingdomEntityMapper.toEntity(inbound);
        entity = saveKingdomPort.execute(entity);

        log.info("Kingdom created: {}", entity);
        return kingdomEntityMapper.toOutbound(entity);
    }

}