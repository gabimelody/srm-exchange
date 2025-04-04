package com.srm.srmexchange.application.dto.exchangerate;

import com.srm.srmexchange.application.dto.coin.CoinOutbound;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRateOutbound {

    private UUID id;
    private BigDecimal vlRate;
    private CoinOutbound coinTo;
    private CoinOutbound coinFrom;

}