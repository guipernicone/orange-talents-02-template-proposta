package com.zup.orange.proposta.entity.card.enums;

public enum CardStatusEnum {
    BLOQUEADO("BLOQUEADO"),
    ATIVO("ATIVO"),
    FALHA("FALHA");

    private String value;

    private CardStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
