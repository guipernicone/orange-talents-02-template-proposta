package com.zup.orange.proposta.client.account.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class AssociateCardRequest {
    @NotBlank(message = "{NotBlank}")
    @JsonProperty("document")
    private String document;
    @NotBlank(message = "{NotBlank}")
    @JsonProperty("nome")
    private String name;
    @NotBlank(message = "{NotBlank}")
    @JsonProperty("proposalId")
    private String proposalId;

    public AssociateCardRequest(
            @NotBlank(message = "{NotBlank}") String document,
            @NotBlank(message = "{NotBlank}") String name,
            @NotBlank(message = "{NotBlank}") String proposalId
    ) {
        this.document = document;
        this.name = name;
        this.proposalId = proposalId;
    }

    public String getDocument() {
        return document;
    }

    public String getName() {
        return name;
    }

    public String getProposalId() {
        return proposalId;
    }
}
