package com.srm.srmexchange.domain.exception;

import com.srm.srmexchange.domain.constants.ErrorCodeEnum;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
public class CoinNotFoundException extends BusinessException {

    public CoinNotFoundException() {
        super(ErrorCodeEnum.PRO001);
    }

}