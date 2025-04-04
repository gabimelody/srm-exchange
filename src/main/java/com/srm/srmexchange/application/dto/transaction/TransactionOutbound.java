package com.srm.srmexchange.application.dto.transaction;

import com.srm.srmexchange.application.dto.coin.CoinOutbound;
import com.srm.srmexchange.application.dto.product.ProductOutbound;
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
public class TransactionOutbound {

    private UUID id;
    private Integer quantity;
    private BigDecimal vlUnit;
    private BigDecimal vlRate;
    private CoinOutbound coinUsed;
    private BigDecimal vlConverted;
    private ProductOutbound product;
    private CoinOutbound coinRequested;

}