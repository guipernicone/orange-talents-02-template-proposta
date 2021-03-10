package com.zup.orange.proposta.client.account;

import com.zup.orange.proposta.client.account.request.AssociateCardRequest;
import com.zup.orange.proposta.client.account.response.AssociateCardResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        value = "account",
        url = "http://localhost:8888"
)
public interface AccountClient {

    @PostMapping("/api/cartoes")
    AssociateCardResponse associateCard(AssociateCardRequest cardRequest);
}
