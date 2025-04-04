package com.srm.srmexchange.domain.port.in.transaction;

import com.srm.srmexchange.application.dto.transaction.TransactionOutbound;

import java.util.List;

public interface SearchTransactionPort {

    List<TransactionOutbound> execute();

}