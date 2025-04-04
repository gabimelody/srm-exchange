package com.srm.srmexchange.application.usecase.transaction;

import com.srm.srmexchange.application.dto.transaction.TransactionOutbound;
import com.srm.srmexchange.application.mapper.TransactionEntityMapper;
import com.srm.srmexchange.domain.domain.TransactionEntity;
import com.srm.srmexchange.domain.port.in.transaction.SearchTransactionByIdPort;
import com.srm.srmexchange.domain.port.out.transaction.FindByIdTransactionPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class SearchTransactionByIdUseCase implements SearchTransactionByIdPort {

    private final TransactionEntityMapper transactionEntityMapper;
    private final FindByIdTransactionPort findByIdTransactionPort;

    @Override
    public TransactionOutbound execute(UUID id) {
        log.info("Searching transaction by id: {}", id);
        return findByIdTransactionPort.execute(id)
                .map(this::mapToOutbound)
                .orElse(null);
    }

    private TransactionOutbound mapToOutbound(TransactionEntity entity) {
        log.info("Transaction found: {}", entity);
        return transactionEntityMapper.toOutbound(entity);
    }

}