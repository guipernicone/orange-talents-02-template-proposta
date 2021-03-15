package com.zup.orange.proposta.client.auth.request;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String refreshToken;

    @Deprecated
    public LoginRequest() {
    }

    public LoginRequest(String username, String password, String refreshToken) {
        this.username = username;
        this.password = password;
        this.refreshToken = refreshToken;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
