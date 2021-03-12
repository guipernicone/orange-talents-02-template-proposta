package com.zup.orange.proposta.client.auth;

import com.zup.orange.proposta.client.auth.request.AuthRequest;
import com.zup.orange.proposta.client.auth.response.AuthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@FeignClient(
        value = "auth",
        url = "${auth.client.url}"
)
public interface AuthClient {

    @PostMapping
    Optional<AuthResponse> auth(MultiValueMap<String, String> authRequest);
}
