package com.zup.orange.proposta.client.account.response;

import com.zup.orange.proposta.entity.card.Renegotiation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AssociateCardRenegotiationResponse {

    @NotBlank(message = "{NotBlank}")
    private String id;

    @Positive
    private int quantidade;

    @NotNull(message = "{NotNull}")
    @Positive
    private BigDecimal valor;

    @NotNull(message = "{NotNull}")
    private LocalDateTime dataDeCriacao;

    public String getId() {
        return id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }

    public Renegotiation toModel(){
        return new Renegotiation(this.id, this.quantidade, this.valor, this.dataDeCriacao);
    }
}
