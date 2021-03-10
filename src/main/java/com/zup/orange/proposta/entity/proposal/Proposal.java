package com.zup.orange.proposta.entity.proposal;

import com.zup.orange.proposta.client.analyze.response.AnalyzeResponse;
import com.zup.orange.proposta.entity.card.Card;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
@Table(name = "proposal")
public class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "{NotBlank}")
    private String name;
    @NotBlank(message = "{NotBlank}")
    @Email
    private String email;
    @NotBlank(message = "{NotBlank}")
    private String document;
    @NotNull(message = "{NotNull}")
    @Positive
    private BigDecimal salary;
    @NotNull(message = "{NotNull}")
    @Embedded
    private Address address;
    @Enumerated(EnumType.STRING)
    private ProposalStatusEnum status;
    @OneToOne(mappedBy = "proposal", cascade = CascadeType.ALL)
    private Card card;

    @Deprecated
    public Proposal() {
    }

    public Proposal(
            @NotBlank(message = "{NotBlank}") String name,
            @NotBlank(message = "{NotBlank}") @Email String email,
            @NotBlank(message = "{NotBlank}") String document,
            @Positive @NotNull(message = "{NotNull}") BigDecimal salary,
            @NotNull(message = "{NotNull}") Address address
    ) {
        this.name = name;
        this.email = email;
        this.document = document;
        this.salary = salary;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDocument() {
        return document;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public Address getAddress() {
        return address;
    }

    public ProposalStatusEnum getStatus() {
        return status;
    }

    public Card getCard() {
        return card;
    }

    public void updateStatus(AnalyzeResponse response){
        this.status = response.getResult().getValue();
    }

    public void updateCard(Card card){
        this.card = card;
    }
}
