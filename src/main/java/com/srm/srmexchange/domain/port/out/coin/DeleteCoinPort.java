package com.srm.srmexchange.domain.port.out.coin;

import com.srm.srmexchange.domain.domain.CoinEntity;

public interface DeleteCoinPort {

    void execute(CoinEntity entity);

}