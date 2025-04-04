package com.srm.srmexchange.infrastructure.controller;

import com.srm.openapi.api.TransactionsApi;
import com.srm.representation.TransactionRequestRepresentation;
import com.srm.representation.TransactionResponseRepresentation;
import com.srm.srmexchange.application.dto.transaction.TransactionInbound;
import com.srm.srmexchange.application.dto.transaction.TransactionOutbound;
import com.srm.srmexchange.domain.exception.ResourceNotFoundException;
import com.srm.srmexchange.domain.port.in.transaction.CreateTransactionPort;
import com.srm.srmexchange.domain.port.in.transaction.SearchTransactionByIdPort;
import com.srm.srmexchange.domain.port.in.transaction.SearchTransactionPort;
import com.srm.srmexchange.infrastructure.controller.mapper.TransactionRepresentationMapper;
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
public class TransactionController implements TransactionsApi {

    private final CreateTransactionPort createTransactionPort;
    private final SearchTransactionPort searchTransactionPort;
    private final SearchTransactionByIdPort searchTransactionByIdPort;
    private final TransactionRepresentationMapper transactionRepresentationMapper;

    @Override
    public ResponseEntity<TransactionResponseRepresentation> createTransaction(TransactionRequestRepresentation transactionRequestRepresentation) {
        log.info("Received request to create transaction: {}", transactionRequestRepresentation);
        TransactionInbound inbound = transactionRepresentationMapper.toInbound(transactionRequestRepresentation);
        TransactionOutbound outbound = createTransactionPort.execute(inbound);
        TransactionResponseRepresentation representation = transactionRepresentationMapper.toRepresentation(outbound);

        log.info("Returning response of created transaction: {}", representation);
        return ResponseEntity.status(HttpStatus.CREATED).body(representation);
    }

    @Override
    public ResponseEntity<List<TransactionResponseRepresentation>> searchTransactions() {
        log.info("Received request to search all transactions");
        List<TransactionResponseRepresentation> representation = searchTransactionPort.execute().stream()
                .map(transactionRepresentationMapper::toRepresentation)
                .toList();

        log.info("Returning {} transactions founded", representation.size());
        return ResponseEntity.ok(representation);
    }

    @Override
    public ResponseEntity<TransactionResponseRepresentation> findTransactionById(UUID uuid) {
        log.info("Received request to search transaction by uuid: {}", uuid);
        TransactionResponseRepresentation representation = Optional.ofNullable(searchTransactionByIdPort.execute(uuid))
                .map(transactionRepresentationMapper::toRepresentation)
                .orElseThrow(ResourceNotFoundException::new);

        log.info("Returning response of searched transaction: {}", representation);
        return ResponseEntity.ok(representation);
    }

}