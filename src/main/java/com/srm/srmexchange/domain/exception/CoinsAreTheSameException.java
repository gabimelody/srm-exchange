package com.srm.srmexchange.domain.exception;

import com.srm.srmexchange.domain.constants.ErrorCodeEnum;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
public class CoinsAreTheSameException extends BusinessException {

    public CoinsAreTheSameException() {
        super(ErrorCodeEnum.EXC002);
    }

}