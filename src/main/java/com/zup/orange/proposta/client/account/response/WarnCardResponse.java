package com.zup.orange.proposta.client.account.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zup.orange.proposta.entity.card.enums.WarnStatusEnum;

public class WarnCardResponse {

    @JsonProperty("resultado")
    private WarnStatusEnum result;

    public WarnStatusEnum getResult(){
        return result;
    }

}
