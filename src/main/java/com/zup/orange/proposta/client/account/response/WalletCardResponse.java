package com.zup.orange.proposta.client.account.response;

import com.zup.orange.proposta.entity.card.enums.WalletStatusEnum;

public class WalletCardResponse {
    private WalletStatusEnum resultado;
    private String id;

    @Deprecated
    public WalletCardResponse() {
    }

    public WalletCardResponse(WalletStatusEnum resultado, String id) {
        this.resultado = resultado;
        this.id = id;
    }

    public WalletStatusEnum getResultado() {
        return resultado;
    }

    public String getId() {
        return id;
    }
}
