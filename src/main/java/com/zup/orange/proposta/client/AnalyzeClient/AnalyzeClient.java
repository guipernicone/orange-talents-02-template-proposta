package com.zup.orange.proposta.client.AnalyzeClient;

import com.zup.orange.proposta.client.AnalyzeClient.request.AnalyzeRequest;
import com.zup.orange.proposta.client.AnalyzeClient.response.AnalyzeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        value = "analysis",
        url = "http://localhost:9999"
)
public interface AnalyzeClient {

    @PostMapping("/api/solicitacao")
    AnalyzeResponse analyse(AnalyzeRequest analyseRequest);
}
