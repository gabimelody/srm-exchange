package com.srm.srmexchange.domain.port.in.coin;

import com.srm.srmexchange.application.dto.coin.CoinOutbound;

import java.util.UUID;

public interface SearchCoinByIdPort {

    CoinOutbound execute(UUID id);

}