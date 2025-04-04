package com.srm.srmexchange.domain.exception;

import com.srm.srmexchange.domain.constants.ErrorCodeEnum;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
public class ExchangeRateAlreadyExistException extends BusinessException {

    public ExchangeRateAlreadyExistException() {
        super(ErrorCodeEnum.EXC001);
    }

}