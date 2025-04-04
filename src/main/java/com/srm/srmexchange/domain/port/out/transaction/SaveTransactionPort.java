package com.srm.srmexchange.domain.port.out.transaction;

import com.srm.srmexchange.domain.domain.TransactionEntity;

public interface SaveTransactionPort {

    TransactionEntity execute(TransactionEntity entity);

}