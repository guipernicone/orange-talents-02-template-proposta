package com.zup.orange.proposta.client.analyze.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zup.orange.proposta.entity.proposal.Proposal;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;

public class AnalyzeRequest {

    @NotBlank(message = "{NotBlank}")
    @JsonProperty("documento")
    private String document;
    @NotBlank(message = "{NotBlank}")
    @JsonProperty("nome")
    private String name;
    @NotBlank(message = "{NotBlank}")
    @JsonProperty("idProposta")
    private String id;

    @Deprecated
    public AnalyzeRequest() {}

    public AnalyzeRequest(Proposal proposal) {
        Assert.notNull(proposal, "The proposal cannot be null");

        this.document = proposal.getDocument();
        this.name = proposal.getName();
        this.id = String.valueOf(proposal.getId());
    }

    public String getDocument() {
        return document;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
