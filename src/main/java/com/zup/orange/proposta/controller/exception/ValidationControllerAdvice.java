package com.zup.orange.proposta.controller.exception;

import com.zup.orange.proposta.controller.exception.MethodArgumentNotValid.MethodArgumentNotValidError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler( MethodArgumentNotValidException.class)
    public MethodArgumentNotValidError handlerValidationError(MethodArgumentNotValidException exception){
        MethodArgumentNotValidError validationError = new MethodArgumentNotValidError();
        validationError.buildError(exception);
        return validationError;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(IllegalArgumentException.class)
    public String handlerValidationError(IllegalArgumentException exception){
        return exception.getMessage();
    }
}
