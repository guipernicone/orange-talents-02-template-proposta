package com.zup.orange.proposta.client.account.response;

import com.zup.orange.proposta.entity.card.Installment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class AssociateCardInstallmentResponse {

    @NotBlank(message = "{NotBlank}")
    private String id;

    @Positive
    private int quantidade;

    @NotNull(message = "{NotNull}")
    @Positive
    private BigDecimal valor;

    public String getId() {
        return id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Installment toModel(){
        return new Installment(this.id, this.quantidade, this.valor);
    }
}
