package com.zup.orange.proposta.client.analyze;

import com.zup.orange.proposta.client.analyze.request.AnalyzeRequest;
import com.zup.orange.proposta.client.analyze.response.AnalyzeResponse;
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
