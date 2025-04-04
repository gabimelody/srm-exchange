package com.srm.srmexchange.infrastructure.repository.adapter.transaction;

import com.srm.srmexchange.domain.domain.TransactionEntity;
import com.srm.srmexchange.domain.port.out.transaction.SaveTransactionPort;
import com.srm.srmexchange.infrastructure.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SaveTransactionAdapter implements SaveTransactionPort {

    private final TransactionRepository repository;

    @Override
    public TransactionEntity execute(TransactionEntity entity) {
        log.info("Creating entity: {}", entity);
        entity = repository.save(entity);
        log.info("Entity created: {}", entity);

        return entity;
    }

}