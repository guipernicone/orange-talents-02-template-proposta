package com.zup.orange.proposta.entity.card;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "warning")
public class Warning {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull(message = "{NotNull}")
    private LocalDate validUntil;
    @NotBlank(message = "{NotBlank}")
    private String target;
    @ManyToOne
    private Card card;

    @Deprecated
    public Warning() {
    }

    public Warning(
            @NotNull(message = "{NotNull}") LocalDate validUntil,
            @NotBlank(message = "{NotBlank}") String target
    ) {
        this.validUntil = validUntil;
        this.target = target;
    }

    public long getId() {
        return id;
    }

    public LocalDate getValidUntil() {
        return validUntil;
    }

    public String getTarget() {
        return target;
    }

    public Card getCard() {
        return card;
    }
}
