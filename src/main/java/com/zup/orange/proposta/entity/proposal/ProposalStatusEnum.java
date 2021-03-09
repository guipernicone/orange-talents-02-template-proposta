package com.zup.orange.proposta.entity.proposal;

public enum ProposalStatusEnum {
    NAO_ELEGIVEL("NAO_ELEGIVEL"),
    ELEGIVEL("ELEGIVEL");

    private String value;

    private ProposalStatusEnum(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
