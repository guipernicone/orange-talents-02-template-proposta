package com.zup.orange.proposta.entity.card;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
@Table(name = "installment")
public class Installment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Positive
    private int quantity;
    @Positive
    private BigDecimal value;
    @ManyToOne
    private Card card;

    @Deprecated
    public Installment() {
    }

    public Installment(
            @Positive int quantity,
            @Positive BigDecimal value
    ) {
        this.quantity = quantity;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Card getCard() {
        return card;
    }
}
