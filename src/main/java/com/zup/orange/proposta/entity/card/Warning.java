package com.zup.orange.proposta.entity.card;

import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "warning")
public class Warning {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull(message = "{NotNull}")
    private LocalDate endDate;

    @NotBlank(message = "{NotBlank}")
    private String destination;

    @NotBlank(message = "{NotBlank}")
    private String clientIP;

    @NotBlank(message = "{NotBlank}")
    private String responsibleSystem;

    @NotNull(message = "{NotNull}")
    private LocalDateTime creationTime;

    @ManyToOne
    private Card card;

    @Deprecated
    public Warning() {
    }

    public Warning(
            @NotNull(message = "{NotNull}") LocalDate endDate,
            @NotBlank(message = "{NotBlank}") String destination,
            @NotBlank(message = "{NotBlank}") String clientIP,
            @NotBlank(message = "{NotBlank}") String system,
            Card card
    ) {
        this.endDate = endDate;
        this.destination = destination;
        this.clientIP = clientIP;
        this.creationTime = LocalDateTime.now();
        this.responsibleSystem = system;
        this.card = card;
    }

    public long getId() {
        return id;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getDestination() {
        return destination;
    }

    public String getClientIP() {
        return clientIP;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public String getResponsibleSystem() {
        return responsibleSystem;
    }

    public Card getCard() {
        return card;
    }
}
