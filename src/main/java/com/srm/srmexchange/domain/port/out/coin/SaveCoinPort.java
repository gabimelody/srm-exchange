package com.srm.srmexchange.domain.port.out.coin;

import com.srm.srmexchange.domain.domain.CoinEntity;

public interface SaveCoinPort {

    CoinEntity execute(CoinEntity entity);

}