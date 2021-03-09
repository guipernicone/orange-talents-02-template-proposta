package com.zup.orange.proposta.entity.card;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Entity
@Table(name = "duedate")
public class DueDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Positive
    private int day;
    @NotNull(message = "{NotNull}")
    private LocalDateTime creationDate;
    @ManyToOne
    private Card card;

    @Deprecated
    public DueDate() {
    }

    public DueDate(
            @Positive int day,
            @NotNull(message = "{NotNull}") LocalDateTime creationDate
    ) {
        this.day = day;
        this.creationDate = creationDate;
    }

    public long getId() {
        return id;
    }

    public int getDay() {
        return day;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Card getCard() {
        return card;
    }
}
