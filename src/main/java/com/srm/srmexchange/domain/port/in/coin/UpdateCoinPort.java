package com.srm.srmexchange.domain.port.in.coin;

import com.srm.srmexchange.application.dto.coin.CoinInbound;
import com.srm.srmexchange.application.dto.coin.CoinOutbound;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public interface UpdateCoinPort {

    CoinOutbound execute(
            @NotNull(message = "Coin id is required") UUID uuid,
            @Valid CoinInbound inbound
    );

}