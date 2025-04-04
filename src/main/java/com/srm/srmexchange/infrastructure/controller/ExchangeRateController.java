package com.srm.srmexchange.infrastructure.controller;

import com.srm.openapi.api.ExchangeRatesApi;
import com.srm.representation.ExchangeRateRequestRepresentation;
import com.srm.representation.ExchangeRateResponseRepresentation;
import com.srm.srmexchange.application.dto.exchangerate.ExchangeRateInbound;
import com.srm.srmexchange.application.dto.exchangerate.ExchangeRateOutbound;
import com.srm.srmexchange.domain.exception.ResourceNotFoundException;
import com.srm.srmexchange.domain.port.in.exchangerate.*;
import com.srm.srmexchange.infrastructure.controller.mapper.ExchangeRateRepresentationMapper;
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
public class ExchangeRateController implements ExchangeRatesApi {

    private final CreateExchangeRatePort createExchangeRatePort;
    private final RemoveExchangeRatePort removeExchangeRatePort;
    private final SearchExchangeRatePort searchExchangeRatePort;
    private final UpdateExchangeRatePort updateExchangeRatePort;
    private final SearchExchangeRateByIdPort searchExchangeRateByIdPort;
    private final ExchangeRateRepresentationMapper exchangeRateRepresentationMapper;

    @Override
    public ResponseEntity<ExchangeRateResponseRepresentation> createExchangeRate(ExchangeRateRequestRepresentation exchangeRateRequestRepresentation) {
        log.info("Received request to create exchange rate: {}", exchangeRateRequestRepresentation);
        ExchangeRateInbound inbound = exchangeRateRepresentationMapper.toInbound(exchangeRateRequestRepresentation);
        ExchangeRateOutbound outbound = createExchangeRatePort.execute(inbound);
        ExchangeRateResponseRepresentation representation = exchangeRateRepresentationMapper.toRepresentation(outbound);

        log.info("Returning response of created exchange rate: {}", representation);
        return ResponseEntity.status(HttpStatus.CREATED).body(representation);
    }

    @Override
    public ResponseEntity<List<ExchangeRateResponseRepresentation>> searchExchangeRates() {
        log.info("Received request to search all exchange rates");
        List<ExchangeRateResponseRepresentation> representation = searchExchangeRatePort.execute().stream()
                .map(exchangeRateRepresentationMapper::toRepresentation)
                .toList();

        log.info("Returning {} exchange rates founded", representation.size());
        return ResponseEntity.ok(representation);
    }

    @Override
    public ResponseEntity<ExchangeRateResponseRepresentation> findExchangeRateById(UUID uuid) {
        log.info("Received request to search exchange rate by uuid: {}", uuid);
        ExchangeRateResponseRepresentation representation = Optional.ofNullable(searchExchangeRateByIdPort.execute(uuid))
                .map(exchangeRateRepresentationMapper::toRepresentation)
                .orElseThrow(ResourceNotFoundException::new);

        log.info("Returning response of searched exchange rate: {}", representation);
        return ResponseEntity.ok(representation);
    }

    @Override
    public ResponseEntity<ExchangeRateResponseRepresentation> updateExchangeRate(UUID uuid, ExchangeRateRequestRepresentation exchangeRateRequestRepresentation) {
        log.info("Received request to update exchange rate: {}", exchangeRateRequestRepresentation);
        ExchangeRateInbound inbound = exchangeRateRepresentationMapper.toInbound(exchangeRateRequestRepresentation);
        ExchangeRateResponseRepresentation representation = Optional.ofNullable(updateExchangeRatePort.execute(uuid, inbound))
                .map(exchangeRateRepresentationMapper::toRepresentation)
                .orElseThrow(ResourceNotFoundException::new);

        log.info("Returning response of updated exchange rate: {}", representation);
        return ResponseEntity.ok(representation);
    }

    @Override
    public ResponseEntity<Void> removeExchangeRate(UUID uuid) {
        log.info("Received request to remove exchange rate uuid: {}", uuid);
        if (!removeExchangeRatePort.execute(uuid)) {
            throw new ResourceNotFoundException();
        }

        return ResponseEntity.noContent().build();
    }

}