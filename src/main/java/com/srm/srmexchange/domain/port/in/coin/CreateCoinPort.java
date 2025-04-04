package com.srm.srmexchange.domain.port.in.coin;

import com.srm.srmexchange.application.dto.coin.CoinInbound;
import com.srm.srmexchange.application.dto.coin.CoinOutbound;
import jakarta.validation.Valid;

public interface CreateCoinPort {

    CoinOutbound execute(@Valid CoinInbound inbound);

}