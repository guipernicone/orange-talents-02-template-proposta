package com.zup.orange.proposta.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zup.orange.proposta.client.auth.AuthClient;
import com.zup.orange.proposta.client.auth.request.AuthRequest;
import com.zup.orange.proposta.client.auth.request.LoginRequest;
import com.zup.orange.proposta.client.auth.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Value("${auth.grant_type}")
    private String grantType;

    @Value("${auth.client_id}")
    private String clientId;

    @Value("${auth.client_secret}")
    private String clientSecret;

    @Value("${auth.scope}")
    private String scope;

    @Autowired
    AuthClient authClient;

    @PostMapping
    public ResponseEntity<?> auth(@RequestBody @Valid LoginRequest loginRequest){
        MultiValueMap<String, String> authRequest = new LinkedMultiValueMap<>();
        if (loginRequest.getRefreshToken() == null){
            authRequest.add("grant_type", this.grantType);
            authRequest.add("client_secret", this.clientSecret);
            authRequest.add("username", loginRequest.getUsername());
            authRequest.add("password", loginRequest.getPassword());
        }
        else{
            authRequest.add("grant_type", "refresh_token");
            authRequest.add("refresh_token", loginRequest.getRefreshToken());
        }
        authRequest.add("scope", this.scope);
        authRequest.add("client_id", this.clientId);

        Optional<AuthResponse> authResponseOptional = authClient.auth(authRequest);

        if (authResponseOptional.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(authResponseOptional.get());
    }
}
