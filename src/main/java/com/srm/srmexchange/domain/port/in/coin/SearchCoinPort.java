package com.srm.srmexchange.domain.port.in.coin;

import com.srm.srmexchange.application.dto.coin.CoinOutbound;

import java.util.List;

public interface SearchCoinPort {

    List<CoinOutbound> execute();

}