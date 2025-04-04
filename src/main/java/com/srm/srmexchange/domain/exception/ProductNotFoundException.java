package com.srm.srmexchange.domain.exception;

import com.srm.srmexchange.domain.constants.ErrorCodeEnum;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
public class ProductNotFoundException extends BusinessException {

    public ProductNotFoundException() {
        super(ErrorCodeEnum.PRO001);
    }

}