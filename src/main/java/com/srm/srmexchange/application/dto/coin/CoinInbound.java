package com.srm.srmexchange.application.dto.coin;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoinInbound {

    @NotNull(message = "Coin name is required")
    private String name;

}