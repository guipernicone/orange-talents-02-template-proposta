package com.zup.orange.proposta.entity.card;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "blocked")
public class Blocked {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "{NotNull}")
    private LocalDateTime blockedIn;

    @NotBlank(message = "{NotBlank}")
    private String responsibleSystem;

    private String clientIp;
    @ManyToOne
    private Card card;

    @Deprecated
    public Blocked() {
    }

    public Blocked(
            @NotNull(message = "{NotNull}") LocalDateTime blockedIn,
            @NotBlank(message = "{NotBlank}") String responsibleSystem,
            String clientIp,
            Card card
    ) {
        this.blockedIn = blockedIn;
        this.responsibleSystem = responsibleSystem;
        this.clientIp = clientIp;
        this.card = card;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getBlockedIn() {
        return blockedIn;
    }

    public String getResponsibleSystem() {
        return responsibleSystem;
    }

    public String getClientIp() {
        return clientIp;
    }

    public Card getCard() {
        return card;
    }
}
