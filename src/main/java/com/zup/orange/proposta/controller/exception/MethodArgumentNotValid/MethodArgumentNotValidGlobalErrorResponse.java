package com.zup.orange.proposta.controller.exception.MethodArgumentNotValid;

public class MethodArgumentNotValidGlobalErrorResponse extends MethodArgumentNotValidErrorResponse {
    private String type;

    public MethodArgumentNotValidGlobalErrorResponse(String type, String defaultMessage) {
        super(defaultMessage);
        this.type = type;
    }

    public String getType() {
        return type;
    }
}