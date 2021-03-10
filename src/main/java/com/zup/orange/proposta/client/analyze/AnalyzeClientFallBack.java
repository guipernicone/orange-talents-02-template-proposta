package com.zup.orange.proposta.client.analyze;

import com.zup.orange.proposta.client.analyze.request.AnalyzeRequest;
import com.zup.orange.proposta.client.analyze.response.AnalyseStatusEnum;
import com.zup.orange.proposta.client.analyze.response.AnalyzeResponse;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class AnalyzeClientFallBack implements AnalyzeClient {

    @Override
    public AnalyzeResponse analyse(AnalyzeRequest analyseRequest){
        return new AnalyzeResponse(
                analyseRequest.getDocument(),
                analyseRequest.getName(),
                AnalyseStatusEnum.COM_RESTRICAO,
                analyseRequest.getId()
        );
    }

}
