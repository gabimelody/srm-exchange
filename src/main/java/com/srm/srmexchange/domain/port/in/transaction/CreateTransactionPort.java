package com.srm.srmexchange.domain.port.in.transaction;

import com.srm.srmexchange.application.dto.transaction.TransactionInbound;
import com.srm.srmexchange.application.dto.transaction.TransactionOutbound;
import jakarta.validation.Valid;

public interface CreateTransactionPort {

    TransactionOutbound execute(@Valid TransactionInbound inbound);

}