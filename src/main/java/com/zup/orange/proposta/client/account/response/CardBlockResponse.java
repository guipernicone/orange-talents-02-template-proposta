package com.zup.orange.proposta.client.account.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zup.orange.proposta.entity.card.CardStatusEnum;

public class CardBlockResponse {

    @JsonProperty("resultado")
    private CardStatusEnum result;

    @Deprecated
    public CardBlockResponse() {
    }

    public CardBlockResponse(CardStatusEnum result) {
        this.result = result;
    }

    public CardStatusEnum getResult() {
        return result;
    }
}
