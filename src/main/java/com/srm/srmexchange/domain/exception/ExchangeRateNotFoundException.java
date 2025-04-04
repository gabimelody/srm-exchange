package com.srm.srmexchange.domain.exception;

import com.srm.srmexchange.domain.constants.ErrorCodeEnum;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
public class ExchangeRateNotFoundException extends BusinessException {

    public ExchangeRateNotFoundException() {
        super(ErrorCodeEnum.EXC003);
    }

}