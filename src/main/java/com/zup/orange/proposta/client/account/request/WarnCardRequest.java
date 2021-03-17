package com.zup.orange.proposta.client.account.request;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class WarnCardRequest {

    @NotBlank(message = "{NotBlank}")
    private String destino;
    @NotNull(message = "{NotNull}")
    @Future
    private LocalDate validoAte;

    @Deprecated
    public WarnCardRequest() { }

    public WarnCardRequest(
            @NotBlank(message = "{NotBlank}") String destino,
            @NotNull(message = "{NotNull}") @Future LocalDate validoAte
    ) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }
}
