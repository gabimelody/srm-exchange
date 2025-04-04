package com.srm.srmexchange.application.dto.exchangerate;

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
public class ExchangeRateInbound {

    @NotNull(message = "Coin to is required")
    private UUID idCoinTo;

    @NotNull(message = "Coin from is required")
    private UUID idCoinFrom;

    @NotNull(message = "Rate is required")
    private BigDecimal vlRate;

}