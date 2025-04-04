package com.srm.srmexchange.application.dto.transaction;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionInbound {

    @NotNull(message = "Product id is required")
    private UUID idProduct;

    @NotNull(message = "Coin used id is required")
    private UUID idCoinUsed;

    @NotNull(message = "Quantity is required")
    private Integer quantity;

}