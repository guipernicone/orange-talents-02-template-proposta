package com.zup.orange.proposta.entity.card.request;

import com.zup.orange.proposta.entity.card.Card;
import com.zup.orange.proposta.entity.card.Wallet;
import com.zup.orange.proposta.entity.card.enums.WalletEnum;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class WalletCardRequest {

    @Email
    private String email;
    @NotNull(message = "{NotNull}")
    private WalletEnum wallet;

    @Deprecated
    public WalletCardRequest() {
    }

    public WalletCardRequest(@Email String email, @NotNull(message = "{NotNull}") WalletEnum wallet) {
        this.email = email;
        this.wallet = wallet;
    }

    public String getEmail() {
        return email;
    }

    public WalletEnum getWallet() {
        return wallet;
    }

    public Wallet toModel(String issuer, Card card){
        return new Wallet(
            this.getWallet(),
            this.getEmail(),
            issuer,
            card
        );
    }
}
