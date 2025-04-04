package com.srm.srmexchange.domain.port.out.coin;

import com.srm.srmexchange.domain.domain.CoinEntity;

import java.util.List;

public interface FindAllCoinPort {

    List<CoinEntity> execute();

}