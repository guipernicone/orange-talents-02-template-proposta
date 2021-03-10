package com.zup.orange.proposta.client.account.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class AssociateCardRequest {
    @NotBlank(message = "{NotBlank}")
    private String documento;
    @NotBlank(message = "{NotBlank}")
    private String nome;
    @NotBlank(message = "{NotBlank}")
    private String idProposta;

    public AssociateCardRequest(
            @NotBlank(message = "{NotBlank}") String documento,
            @NotBlank(message = "{NotBlank}") String nome,
            @NotBlank(message = "{NotBlank}") String idProposta
    ) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
