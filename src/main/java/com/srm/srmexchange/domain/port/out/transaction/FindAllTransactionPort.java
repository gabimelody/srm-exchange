package com.srm.srmexchange.domain.port.out.transaction;

import com.srm.srmexchange.domain.domain.TransactionEntity;

import java.util.List;

public interface FindAllTransactionPort {

    List<TransactionEntity> execute();

}