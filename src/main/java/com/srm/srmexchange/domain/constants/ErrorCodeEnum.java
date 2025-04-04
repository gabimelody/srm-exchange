package com.srm.srmexchange.domain.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {

    GEN001("This resource does not exist"),
    GEN002("Fields are required"),
    GEN003("The fields are invalid"),

    PRO001("Coin informed does not exist"),
    PRO002("Kingdom informed does not exist");

    private final String description;

}