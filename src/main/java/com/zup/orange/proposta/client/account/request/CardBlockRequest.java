package com.zup.orange.proposta.client.account.request;

public class CardBlockRequest {


    private String sistemaResponsavel;

    public CardBlockRequest(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}
