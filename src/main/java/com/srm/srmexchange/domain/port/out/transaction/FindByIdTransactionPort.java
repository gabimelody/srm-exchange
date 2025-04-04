package com.srm.srmexchange.domain.port.out.transaction;

import com.srm.srmexchange.domain.domain.TransactionEntity;

import java.util.Optional;
import java.util.UUID;

public interface FindByIdTransactionPort {

    Optional<TransactionEntity> execute(UUID id);

}