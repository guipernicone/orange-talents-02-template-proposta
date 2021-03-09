package com.zup.orange.proposta.client.account.response;

import com.zup.orange.proposta.entity.card.Blocked;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class AssociateCardBlockedResponse {
    @NotBlank(message = "{NotBlank}")
    private String id;

    @NotNull(message = "{NotNull}")
    private LocalDateTime bloqueadoEm;

    @NotBlank(message = "{NotBlank}")
    private String sistemaResponsavel;

    @NotNull(message = "{NotNull}")
    private Boolean ativo;

    public String getId() {
        return id;
    }

    public LocalDateTime getBloqueadoEm() {
        return bloqueadoEm;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public Blocked toModel(){
        return new Blocked(this.bloqueadoEm, this. sistemaResponsavel, this.ativo);
    }
}
