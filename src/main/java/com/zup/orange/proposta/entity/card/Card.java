package com.zup.orange.proposta.entity.card;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zup.orange.proposta.entity.biometry.Biometry;
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

    @OneToMany(mappedBy = "card", cascade=CascadeType.MERGE)
    private List<Blocked> blockeds;

    @OneToMany(mappedBy = "card", cascade=CascadeType.MERGE)
    private List<Warning> warnings;

    @OneToMany(mappedBy = "card", cascade=CascadeType.MERGE)
    private List<Wallet> wallets;

    @OneToMany(mappedBy = "card", cascade=CascadeType.MERGE)
    private List<Installment> installments;

    @OneToOne(mappedBy = "card", cascade=CascadeType.MERGE)
    private Renegotiation renegotiations;

    @OneToOne(cascade=CascadeType.PERSIST)
    private DueDate dueDates;

    @NotNull(message = "{NotNull}")
    @OneToOne
    private Proposal proposal;

    @OneToMany(mappedBy = "card", cascade=CascadeType.MERGE)
    private List<Biometry> biometry;

    @Deprecated
    public Card() {
    }

    public Card(
            String cardNumber,
            LocalDateTime issuedIn,
            String holder,
            List<Blocked> blockeds,
            List<Warning> warnings,
            List<Wallet> wallets,
            List<Installment> installments,
            BigDecimal limit,
            Renegotiation renegotiations,
            DueDate dueDates,
            Proposal proposal
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

    public Renegotiation getRenegotiations() {
        return renegotiations;
    }

    public DueDate getDueDates() {
        return dueDates;
    }

    public Proposal getProposal() {
        return proposal;
    }

    public List<Biometry> getBiometry() {
        return biometry;
    }
}
