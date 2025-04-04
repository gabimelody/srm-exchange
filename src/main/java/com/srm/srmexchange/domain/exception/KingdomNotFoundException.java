package com.srm.srmexchange.domain.exception;

import com.srm.srmexchange.domain.constants.ErrorCodeEnum;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
public class KingdomNotFoundException extends BusinessException {

    public KingdomNotFoundException() {
        super(ErrorCodeEnum.PRO002);
    }

}