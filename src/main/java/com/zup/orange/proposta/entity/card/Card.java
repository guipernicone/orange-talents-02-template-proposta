package com.zup.orange.proposta.entity.card;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zup.orange.proposta.entity.proposal.Proposal;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "{NotBlank}")
    private String cardNumber;

    @NotNull(message = "{NotNull}")
    private LocalDateTime issuedIn;

    @NotBlank(message = "{NotBlank}")
    private String holder;

    @Positive
    private BigDecimal limitValue;

    @NotNull(message = "{NotNull}")
    @OneToMany(mappedBy = "card")
    private List<Blocked> blockeds;

    @NotNull(message = "{NotNull}")
    @OneToMany(mappedBy = "card")
    private List<Warning> warnings;

    @NotNull(message = "{NotNull}")
    @OneToMany(mappedBy = "card")
    private List<Wallet> wallets;

    @NotNull(message = "{NotNull}")
    @OneToMany(mappedBy = "card")
    private List<Installment> installments;

    @NotNull(message = "{NotNull}")
    @OneToMany(mappedBy = "card")
    private List<Renegotiation> renegotiations;

    @NotNull(message = "{NotNull}")
    @OneToMany(mappedBy = "card")
    private List<DueDate> dueDates;

    @NotNull(message = "{NotNull}")
    @OneToOne
    private Proposal proposal;

    @Deprecated
    public Card() {
    }

    public Card(
            @NotBlank(message = "{NotBlank}") String cardNumber,
            @NotNull(message = "{NotNull}") LocalDateTime issuedIn,
            @NotBlank(message = "{NotBlank}") String holder,
            @NotNull(message = "{NotNull}") List<Blocked> blockeds,
            @NotNull(message = "{NotNull}") List<Warning> warnings,
            @NotNull(message = "{NotNull}") List<Wallet> wallets,
            @NotNull(message = "{NotNull}") List<Installment> installments,
            @Positive BigDecimal limit,
            @NotNull(message = "{NotNull}") List<Renegotiation> renegotiations,
            @NotNull(message = "{NotNull}") List<DueDate> dueDates,
            @NotNull(message = "{NotNull}") Proposal proposal
    ) {
        this.cardNumber = cardNumber;
        this.issuedIn = issuedIn;
        this.holder = holder;
        this.blockeds = blockeds;
        this.warnings = warnings;
        this.wallets = wallets;
        this.installments = installments;
        this.limitValue = limit;
        this.renegotiations = renegotiations;
        this.dueDates = dueDates;
        this.proposal = proposal;
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

    public List<Blocked> getBlockeds() {
        return blockeds;
    }

    public List<Warning> getWarnings() {
        return warnings;
    }

    public List<Wallet> getWallets() {
        return wallets;
    }

    public List<Installment> getInstallments() {
        return installments;
    }

    public BigDecimal getLimitValue() {
        return limitValue;
    }
    public List<Renegotiation> getRenegotiations() {
        return renegotiations;
    }

    public List<DueDate> getDueDates() {
        return dueDates;
    }

    public Proposal getProposal() {
        return proposal;
    }
}
