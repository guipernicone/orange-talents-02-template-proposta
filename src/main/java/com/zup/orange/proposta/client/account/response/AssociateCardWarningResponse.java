package com.zup.orange.proposta.client.account.response;

import com.zup.orange.proposta.entity.card.Warning;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AssociateCardWarningResponse {

    @NotNull(message = "{NotNull}")
    private LocalDate validoAte;

    @NotBlank(message = "{NotBlank}")
    private String destino;

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public Warning toModel(){
        return new Warning(this.validoAte, this.destino);
    }
}
