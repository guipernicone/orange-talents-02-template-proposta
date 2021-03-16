package com.zup.orange.proposta.client.account.response;

import com.zup.orange.proposta.entity.card.DueDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

public class AssociateCardDueDateResponse {
    @NotBlank(message = "{NotBlank}")
    private String id;

    @Positive
    private int dia;

    private LocalDateTime dataDeCriacao;

    @Deprecated
    public AssociateCardDueDateResponse() {
    }

    public AssociateCardDueDateResponse(
            @NotBlank(message = "{NotBlank}") String id,
            @Positive int dia,
            LocalDateTime dataDeCriacao
    ) {
        this.id = id;
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
    }

    public String getId() {
        return id;
    }

    public int getDia() {
        return dia;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }

    public DueDate toModel(){
        return new DueDate(this.id, this.dia, this.dataDeCriacao);
    }
}
