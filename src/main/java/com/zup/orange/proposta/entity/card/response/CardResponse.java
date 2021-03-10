package com.zup.orange.proposta.entity.card.response;

import com.zup.orange.proposta.entity.card.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CardResponse {
    private long id;
    private String cardNumber;
    private LocalDateTime issuedIn;
    private String holder;
    private BigDecimal limitValue;

    @Deprecated
    public CardResponse() {
    }

    public CardResponse(Card card){
        this.id = card.getId();
        this.cardNumber = card.getCardNumber();
        this.issuedIn = card.getIssuedIn();
        this.holder = card.getHolder();
        this.limitValue = card.getLimitValue();
    }

    public long getId() {
        return id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public LocalDateTime getIssuedIn() {
        return issuedIn;
    }

    public String getHolder() {
        return holder;
    }

    public BigDecimal getLimitValue() {
        return limitValue;
    }
}
