package com.srm.srmexchange.infrastructure.controller;

import com.srm.openapi.api.KingdomsApi;
import com.srm.representation.KingdomRequestRepresentation;
import com.srm.representation.KingdomResponseRepresentation;
import com.srm.srmexchange.application.dto.kingdom.KingdomInbound;
import com.srm.srmexchange.application.dto.kingdom.KingdomOutbound;
import com.srm.srmexchange.domain.exception.ResourceNotFoundException;
import com.srm.srmexchange.domain.port.in.kingdom.*;
import com.srm.srmexchange.infrastructure.controller.mapper.KingdomRepresentationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class KingdomController implements KingdomsApi {

    private final CreateKingdomPort createKingdomPort;
    private final RemoveKingdomPort removeKingdomPort;
    private final SearchKingdomPort searchKingdomPort;
    private final UpdateKingdomPort updateKingdomPort;
    private final SearchKingdomByIdPort searchKingdomByIdPort;
    private final KingdomRepresentationMapper kingdomRepresentationMapper;

    @Override
    public ResponseEntity<KingdomResponseRepresentation> createKingdom(KingdomRequestRepresentation kingdomRequestRepresentation) {
        log.info("Received request to create kingdom: {}", kingdomRequestRepresentation);
        KingdomInbound inbound = kingdomRepresentationMapper.toInbound(kingdomRequestRepresentation);
        KingdomOutbound outbound = createKingdomPort.execute(inbound);
        KingdomResponseRepresentation representation = kingdomRepresentationMapper.toRepresentation(outbound);

        log.info("Returning response of created kingdom: {}", representation);
        return ResponseEntity.status(HttpStatus.CREATED).body(representation);
    }

    @Override
    public ResponseEntity<List<KingdomResponseRepresentation>> searchKingdoms() {
        log.info("Received request to search all kingdoms");
        List<KingdomResponseRepresentation> representation = searchKingdomPort.execute().stream()
                .map(kingdomRepresentationMapper::toRepresentation)
                .toList();

        log.info("Returning {} kingdoms founded", representation.size());
        return ResponseEntity.ok(representation);
    }

    @Override
    public ResponseEntity<KingdomResponseRepresentation> findKingdomById(UUID uuid) {
        log.info("Received request to search kingdom by uuid: {}", uuid);
        KingdomResponseRepresentation representation = Optional.ofNullable(searchKingdomByIdPort.execute(uuid))
                .map(kingdomRepresentationMapper::toRepresentation)
                .orElseThrow(ResourceNotFoundException::new);

        log.info("Returning response of searched kingdom: {}", representation);
        return ResponseEntity.ok(representation);
    }

    @Override
    public ResponseEntity<KingdomResponseRepresentation> updateKingdom(UUID uuid, KingdomRequestRepresentation kingdomRequestRepresentation) {
        log.info("Received request to update kingdom: {}", kingdomRequestRepresentation);
        KingdomInbound inbound = kingdomRepresentationMapper.toInbound(kingdomRequestRepresentation);
        KingdomResponseRepresentation representation = Optional.ofNullable(updateKingdomPort.execute(uuid, inbound))
                .map(kingdomRepresentationMapper::toRepresentation)
                .orElseThrow(ResourceNotFoundException::new);

        log.info("Returning response of updated kingdom: {}", representation);
        return ResponseEntity.ok(representation);
    }

    @Override
    public ResponseEntity<KingdomResponseRepresentation> removeKingdom(UUID uuid) {
        log.info("Received request to remove kingdom uuid: {}", uuid);
        if (!removeKingdomPort.execute(uuid)) {
            throw new ResourceNotFoundException();
        }

        return ResponseEntity.noContent().build();
    }

}