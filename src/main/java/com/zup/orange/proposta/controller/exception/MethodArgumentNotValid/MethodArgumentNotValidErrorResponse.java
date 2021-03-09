package com.zup.orange.proposta.controller.exception.MethodArgumentNotValid;

public class MethodArgumentNotValidErrorResponse {
    private String message;

    public MethodArgumentNotValidErrorResponse(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
