package com.srm.srmexchange.infrastructure.repository.adapter.transaction;

import com.srm.srmexchange.domain.domain.TransactionEntity;
import com.srm.srmexchange.domain.port.out.transaction.FindByIdTransactionPort;
import com.srm.srmexchange.infrastructure.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class FindByIdTransactionAdapter implements FindByIdTransactionPort {

    private final TransactionRepository repository;

    @Override
    public Optional<TransactionEntity> execute(UUID id) {
        log.debug("Finding entity by id: {}", id);
        Optional<TransactionEntity> optional = repository.findById(id);
        log.debug("Entity found: {}", optional.isPresent());

        return optional;
    }

}