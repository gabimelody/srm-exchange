package com.srm.srmexchange.domain.port.in.transaction;

import com.srm.srmexchange.application.dto.transaction.TransactionOutbound;

import java.util.UUID;

public interface SearchTransactionByIdPort {

    TransactionOutbound execute(UUID id);

}