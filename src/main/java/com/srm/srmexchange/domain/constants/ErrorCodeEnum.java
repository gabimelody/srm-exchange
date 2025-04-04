package com.srm.srmexchange.domain.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {

    GEN001("This resource does not exist"),
    GEN002("Fields are required"),
    GEN003("The fields are invalid"),

    COI001("Coin informed does not exist"),

    EXC001("The exchange rate informed already exists"),
    EXC002("The coins informed are the same"),
    EXC003("Exchange rate informed does not exist"),

    KIN001("Kingdom informed does not exist"),

    PRO001("Product informed does not exist");

    private final String description;

}