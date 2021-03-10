package com.zup.orange.proposta.entity.card;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Entity
@Table(name = "duedate")
public class DueDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "{NotBlank}")
    private String dueId;

    @Positive
    private int day;

    @NotNull(message = "{NotNull}")
    private LocalDateTime creationDate;

    @OneToOne
    private Card card;

    @Deprecated
    public DueDate() {
    }

    public DueDate(
            @NotBlank(message = "{NotBlank}") String dueId,
            @Positive int day,
            @NotNull(message = "{NotNull}") LocalDateTime creationDate
    ) {
        this.dueId = dueId;
        this.day = day;
        this.creationDate = creationDate;
    }

    public long getId() {
        return id;
    }

    public String getDueId() {
        return dueId;
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

    @Override
    public String toString() {
        return "DueDate{" +
                "id=" + id +
                ", dueId='" + dueId + '\'' +
                ", day=" + day +
                ", creationDate=" + creationDate +
                ", card=" + card +
                '}';
    }
}
