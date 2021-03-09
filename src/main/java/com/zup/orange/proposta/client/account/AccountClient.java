package com.zup.orange.proposta.client.account;

import com.zup.orange.proposta.client.account.request.AssociateCardRequest;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        value = "account",
        url = "http://localhost:8888"
)
public interface AccountClient {

    String associateCard(AssociateCardRequest cardRequest);
}
