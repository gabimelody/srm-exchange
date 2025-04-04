package com.srm.srmexchange.infrastructure.repository.adapter.transaction;

import com.srm.srmexchange.domain.domain.TransactionEntity;
import com.srm.srmexchange.domain.port.out.transaction.FindAllTransactionPort;
import com.srm.srmexchange.infrastructure.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class FindAllTransactionAdapter implements FindAllTransactionPort {

    private final TransactionRepository repository;

    @Override
    public List<TransactionEntity> execute() {
        log.debug("Finding all entities");
        List<TransactionEntity> list = repository.findAll();
        log.debug("Found {} entities", list.size());

        return list;
    }

}