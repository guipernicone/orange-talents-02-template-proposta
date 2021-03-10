package com.zup.orange.proposta.client.account.response;

import com.zup.orange.proposta.entity.card.Wallet;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class AssociateCardWalletResponse {

    @NotBlank(message = "{NotBlank}")
    private String id;

    @Email
    private String email;

    @NotNull(message = "{NotNull}")
    private LocalDateTime associadaEm;

    @NotBlank(message = "{NotBlank}")
    private String emmissor;

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getAssociadaEm() {
        return associadaEm;
    }

    public String getEmmissor() {
        return emmissor;
    }

    public Wallet toModel(){
        return new Wallet(this.id, this.email, this.associadaEm, this.emmissor);
    }
}
