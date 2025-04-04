package com.srm.srmexchange.infrastructure.controller;

import com.srm.openapi.api.CoinsApi;
import com.srm.representation.CoinRequestRepresentation;
import com.srm.representation.CoinResponseRepresentation;
import com.srm.srmexchange.application.dto.coin.CoinInbound;
import com.srm.srmexchange.application.dto.coin.CoinOutbound;
import com.srm.srmexchange.domain.exception.ResourceNotFoundException;
import com.srm.srmexchange.domain.port.in.coin.*;
import com.srm.srmexchange.infrastructure.controller.mapper.CoinRepresentationMapper;
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
public class CoinController implements CoinsApi {

    private final CreateCoinPort createCoinPort;
    private final RemoveCoinPort removeCoinPort;
    private final SearchCoinPort searchCoinPort;
    private final UpdateCoinPort updateCoinPort;
    private final SearchCoinByIdPort searchCoinByIdPort;
    private final CoinRepresentationMapper coinRepresentationMapper;

    @Override
    public ResponseEntity<CoinResponseRepresentation> createCoin(CoinRequestRepresentation coinRequestRepresentation) {
        log.info("Received request to create coin: {}", coinRequestRepresentation);
        CoinInbound inbound = coinRepresentationMapper.toInbound(coinRequestRepresentation);
        CoinOutbound outbound = createCoinPort.execute(inbound);
        CoinResponseRepresentation representation = coinRepresentationMapper.toRepresentation(outbound);

        log.info("Returning response of created coin: {}", representation);
        return ResponseEntity.status(HttpStatus.CREATED).body(representation);
    }

    @Override
    public ResponseEntity<List<CoinResponseRepresentation>> searchCoins() {
        log.info("Received request to search all coins");
        List<CoinResponseRepresentation> representation = searchCoinPort.execute().stream()
                .map(coinRepresentationMapper::toRepresentation)
                .toList();

        log.info("Returning {} coins founded", representation.size());
        return ResponseEntity.ok(representation);
    }

    @Override
    public ResponseEntity<CoinResponseRepresentation> findCoinById(UUID uuid) {
        log.info("Received request to search coin by uuid: {}", uuid);
        CoinResponseRepresentation representation = Optional.ofNullable(searchCoinByIdPort.execute(uuid))
                .map(coinRepresentationMapper::toRepresentation)
                .orElseThrow(ResourceNotFoundException::new);

        log.info("Returning response of searched coin: {}", representation);
        return ResponseEntity.ok(representation);
    }

    @Override
    public ResponseEntity<CoinResponseRepresentation> updateCoin(UUID uuid, CoinRequestRepresentation coinRequestRepresentation) {
        log.info("Received request to update coin: {}", coinRequestRepresentation);
        CoinInbound inbound = coinRepresentationMapper.toInbound(coinRequestRepresentation);
        CoinResponseRepresentation representation = Optional.ofNullable(updateCoinPort.execute(uuid, inbound))
                .map(coinRepresentationMapper::toRepresentation)
                .orElseThrow(ResourceNotFoundException::new);

        log.info("Returning response of updated coin: {}", representation);
        return ResponseEntity.ok(representation);
    }

    @Override
    public ResponseEntity<CoinResponseRepresentation> removeCoin(UUID uuid) {
        log.info("Received request to remove coin uuid: {}", uuid);
        if (!removeCoinPort.execute(uuid)) {
            throw new ResourceNotFoundException();
        }

        return ResponseEntity.noContent().build();
    }

}