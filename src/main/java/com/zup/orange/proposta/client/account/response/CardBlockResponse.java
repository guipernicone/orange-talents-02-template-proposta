package com.zup.orange.proposta.client.account.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zup.orange.proposta.entity.card.CardStatusEnum;

public class CardBlockResponse {

    @JsonProperty("resultado")
    private CardStatusEnum result;

    public CardStatusEnum getResult() {
        return result;
    }
}
