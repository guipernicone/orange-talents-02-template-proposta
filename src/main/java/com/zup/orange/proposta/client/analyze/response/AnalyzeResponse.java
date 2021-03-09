package com.zup.orange.proposta.client.analyze.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AnalyzeResponse {


    @JsonProperty("documento")
    private String document;
    @JsonProperty("nome")
    private String name;
    @JsonProperty("resultadoSolicitacao")
    private AnalyseStatusEnum result;
    @JsonProperty("idProposta")
    private String id;

    @Deprecated
    public AnalyzeResponse() {
    }

    public AnalyzeResponse(String document, String name, AnalyseStatusEnum result, String id) {
        this.document = document;
        this.name = name;
        this.result = result;
        this.id = id;
    }

    public String getDocument() {
        return document;
    }

    public String getName() {
        return name;
    }

    public AnalyseStatusEnum getResult() {
        return result;
    }

    public String getId() {
        return id;
    }
}
