package com.srm.srmexchange.application.usecase.transaction;

import com.srm.srmexchange.application.dto.transaction.TransactionOutbound;
import com.srm.srmexchange.application.mapper.TransactionEntityMapper;
import com.srm.srmexchange.domain.domain.TransactionEntity;
import com.srm.srmexchange.domain.port.in.transaction.SearchTransactionPort;
import com.srm.srmexchange.domain.port.out.transaction.FindAllTransactionPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class SearchTransactionUseCase implements SearchTransactionPort {

    private final FindAllTransactionPort findAllTransactionPort;
    private final TransactionEntityMapper transactionEntityMapper;

    @Override
    public List<TransactionOutbound> execute() {
        log.info("Searching all transactions");
        return findAllTransactionPort.execute().stream()
                .map(this::mapToOutbound)
                .toList();
    }

    private TransactionOutbound mapToOutbound(TransactionEntity entity) {
        log.info("Transaction found: {}", entity);
        return transactionEntityMapper.toOutbound(entity);
    }

}