package com.zup.orange.proposta.entity.card;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "renegotiation")
public class Renegotiation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "{NotBlank}")
    private String renegotiationId;

    @Positive
    private int quantity;
    @Positive
    private BigDecimal value;
    @NotNull(message = "{NotNull}")
    private LocalDateTime creationDate;

    @Deprecated
    public Renegotiation() {
    }

    public Renegotiation(
            @NotBlank String renegotiationId,
            @Positive int quantity,
            @Positive BigDecimal value,
            @NotNull(message = "{NotNull}") LocalDateTime creationDate
    ) {
        this.renegotiationId = renegotiationId;
        this.quantity = quantity;
        this.value = value;
        this.creationDate = creationDate;
    }

    public long getId() {
        return id;
    }

    public String getRenegotiationId() {
        return renegotiationId;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getValue() {
        return value;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }
}
