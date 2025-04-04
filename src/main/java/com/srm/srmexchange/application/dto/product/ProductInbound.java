package com.srm.srmexchange.application.dto.product;

import jakarta.validation.constraints.NotNull;
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
public class ProductInbound {

    @NotNull(message = "Product name is required")
    private String name;

    @NotNull(message = "Unit value is required")
    private BigDecimal vlUnit;

    @NotNull(message = "Kingdom id is required")
    private UUID idKingdom;

    @NotNull(message = "Coin base id is required")
    private UUID idCoinBase;

}