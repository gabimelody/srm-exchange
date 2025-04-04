package com.srm.srmexchange.application.dto.kingdom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KingdomOutbound {

    private UUID id;
    private String name;

}