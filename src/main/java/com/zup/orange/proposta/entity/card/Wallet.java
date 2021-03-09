package com.zup.orange.proposta.entity.card;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "wallet")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Email
    private String email;
    @NotNull(message = "{NotNull}")
    private LocalDateTime associateIn;
    @NotBlank(message = "{NotBlank}")
    private String issuer;
    @ManyToOne
    private Card card;

    @Deprecated
    public Wallet() {
    }

    public Wallet(
            @Email String email,
            @NotNull(message = "{NotNull}") LocalDateTime associateIn,
            @NotBlank(message = "{NotBlank}") String issuer
    ) {
        this.email = email;
        this.associateIn = associateIn;
        this.issuer = issuer;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getAssociateIn() {
        return associateIn;
    }

    public String getIssuer() {
        return issuer;
    }

    public Card getCard() {
        return card;
    }
}
