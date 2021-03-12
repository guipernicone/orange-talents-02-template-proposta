package com.zup.orange.proposta.client.auth.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class AuthRequest {

    private String username;

    private String password;

    @JsonProperty("grant_type")
    private String grantType;

    @JsonProperty("client_id")
    private String clientId;

    private String scope;

    public AuthRequest(String username, String password, String grantType, String clientId, String scope) {
        this.username = username;
        this.password = password;
        this.grantType = grantType;
        this.clientId = clientId;
        this.scope = scope;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getGrantType() {
        return grantType;
    }

    public String getClientId() {
        return clientId;
    }

    public String getScope() {
        return scope;
    }
}
