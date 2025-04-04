package com.srm.srmexchange.infrastructure.controller.exception;

import com.srm.representation.ErrorResponseRepresentation;
import com.srm.srmexchange.domain.constants.ErrorCodeEnum;
import com.srm.srmexchange.domain.exception.*;
import com.srm.srmexchange.infrastructure.controller.mapper.ErrorRepresentationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ControlExceptionHandler {

    private final ErrorRepresentationMapper errorRepresentationMapper;

    // Errors 4XX
    @ExceptionHandler({
            CoinNotFoundException.class,
            KingdomNotFoundException.class,
            CoinsAreTheSameException.class})
    public ResponseEntity<Object> handleBadRequest(BusinessException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorRepresentationMapper.toRepresentation(exception));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleBadRequest(MethodArgumentNotValidException exception) {
        ErrorResponseRepresentation representation = errorRepresentationMapper.toRepresentation(ErrorCodeEnum.GEN002);
        representation.setFields(getFieldsWithError(exception));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(representation);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleBadRequest(MethodArgumentTypeMismatchException exception) {
        ErrorResponseRepresentation representation = errorRepresentationMapper.toRepresentation(ErrorCodeEnum.GEN003);
        representation.setFields(List.of(exception.getName()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(representation);
    }

    @ExceptionHandler({ ResourceNotFoundException.class })
    public ResponseEntity<Object> handleNotFound(BusinessException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorRepresentationMapper.toRepresentation(exception));
    }

    @ExceptionHandler({ExchangeRateAlreadyExistException.class})
    public ResponseEntity<Object> handleConflict(BusinessException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(errorRepresentationMapper.toRepresentation(exception));
    }

    private List<String> getFieldsWithError(MethodArgumentNotValidException exMethod) {
        BindingResult bindingResult = exMethod.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        return fieldErrors.stream()
                .map(FieldError::getField)
                .toList();
    }

}