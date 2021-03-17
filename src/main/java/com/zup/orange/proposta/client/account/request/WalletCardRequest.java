package com.zup.orange.proposta.client.account.request;

import com.zup.orange.proposta.entity.card.enums.WalletEnum;

public class WalletCardRequest {

    private String email;
    private String carteira;

    @Deprecated
    public WalletCardRequest() {
    }

    public WalletCardRequest(String email, WalletEnum carteira) {
        this.email = email;
        this.carteira = carteira.toString();
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }
}
