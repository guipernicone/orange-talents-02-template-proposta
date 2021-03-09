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
        return new DueDate(this.dia, this.dataDeCriacao);
    }
}
