package com.srm.srmexchange.application.usecase.kingdom;

import com.srm.srmexchange.domain.domain.KingdomEntity;
import com.srm.srmexchange.domain.port.in.kingdom.RemoveKingdomPort;
import com.srm.srmexchange.domain.port.out.kingdom.DeleteKingdomPort;
import com.srm.srmexchange.domain.port.out.kingdom.FindByIdKingdomPort;
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
public class RemoveKingdomUseCase implements RemoveKingdomPort {

    private final DeleteKingdomPort deleteKingdomPort;
    private final FindByIdKingdomPort findByIdKingdomPort;

    @Override
    @Transactional
    public boolean execute(UUID id) {
        log.info("Removing kingdom by id: {}", id);
        return findByIdKingdomPort.execute(id)
                .map(this::remove)
                .orElse(false);
    }

    private boolean remove(KingdomEntity kingdomEntity) {
        deleteKingdomPort.execute(kingdomEntity);
        return true;
    }

}