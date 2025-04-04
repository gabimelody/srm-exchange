package com.srm.srmexchange.application.dto.kingdom;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KingdomInbound {

    @NotNull(message = "Kingdom name is required")
    private String name;

}