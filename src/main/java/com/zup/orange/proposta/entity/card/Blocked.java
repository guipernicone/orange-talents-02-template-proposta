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
    @NotBlank(message = "{NotBlank}")
    private String blockedId;
    @NotNull(message = "{NotNull}")
    private LocalDateTime blockedIn;
    @NotBlank(message = "{NotBlank}")
    private String responsibleSystem;
    @NotBlank(message = "{NotBlank}")
    private boolean active;
    @ManyToOne
    private Card card;

    @Deprecated
    public Blocked() {
    }

    public Blocked(
            @NotBlank(message = "{NotBlank}") String blockedId,
            @NotNull(message = "{NotNull}") LocalDateTime blockedIn,
            @NotBlank(message = "{NotBlank}") String responsibleSystem,
            @NotBlank(message = "{NotBlank}") boolean active
    ) {
        this.blockedId = blockedId;
        this.blockedIn = blockedIn;
        this.responsibleSystem = responsibleSystem;
        this.active = active;
    }

    public long getId() {
        return id;
    }

    public String getBlockedId() {
        return blockedId;
    }

    public LocalDateTime getBlockedIn() {
        return blockedIn;
    }

    public String getResponsibleSystem() {
        return responsibleSystem;
    }

    public boolean isActive() {
        return active;
    }

    public Card getCard() {
        return card;
    }
}
