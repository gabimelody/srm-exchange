package com.srm.srmexchange.infrastructure.controller.mapper;

import com.srm.representation.ErrorResponseRepresentation;
import com.srm.srmexchange.domain.constants.ErrorCodeEnum;
import com.srm.srmexchange.domain.exception.BusinessException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ErrorRepresentationMapper {

    @Named("formatCode")
    default String formatCode(ErrorCodeEnum errorCodeEnum) {
        return errorCodeEnum.name();
    }

    @Mapping(target = "description", source = "errorCodeEnum.description")
    @Mapping(target = "code", source = "errorCodeEnum", qualifiedByName = "formatCode")
    ErrorResponseRepresentation toRepresentation(ErrorCodeEnum errorCodeEnum);

    @Mapping(target = "fields", ignore = true)
    ErrorResponseRepresentation toRepresentation(BusinessException businessException);

}