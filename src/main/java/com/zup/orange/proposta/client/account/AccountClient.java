package com.zup.orange.proposta.client.account;

import com.zup.orange.proposta.client.account.request.AssociateCardRequest;
import com.zup.orange.proposta.client.account.request.CardBlockRequest;
import com.zup.orange.proposta.client.account.request.WarnCardRequest;
import com.zup.orange.proposta.client.account.response.AssociateCardResponse;
import com.zup.orange.proposta.client.account.response.CardBlockResponse;
import com.zup.orange.proposta.client.account.response.WarnCardResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        value = "account",
        url = "${account.client.url}"
)
public interface AccountClient {

    @PostMapping("/api/cartoes")
    AssociateCardResponse associateCard(AssociateCardRequest cardRequest);

    @PostMapping("/api/cartoes/{id}/bloqueios")
    CardBlockResponse blockCard(@PathVariable String id, CardBlockRequest cardBlockRequest);

    @PostMapping("/api/cartoes/{id}/avisos")
    WarnCardResponse warnCard(@PathVariable String id, WarnCardRequest warnCardRequest);
}
