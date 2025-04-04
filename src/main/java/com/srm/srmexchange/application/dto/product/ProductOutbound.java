package com.srm.srmexchange.application.dto.product;

import com.srm.srmexchange.application.dto.coin.CoinOutbound;
import com.srm.srmexchange.application.dto.kingdom.KingdomOutbound;
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
public class ProductOutbound {

    private UUID id;
    private String name;
    private BigDecimal vlUnit;
    private CoinOutbound coinBase;
    private KingdomOutbound kingdom;

}