package com.zup.orange.proposta.entity.proposal.response;

import com.zup.orange.proposta.entity.card.Card;
import com.zup.orange.proposta.entity.card.response.CardResponse;
import com.zup.orange.proposta.entity.proposal.Proposal;
import com.zup.orange.proposta.entity.proposal.ProposalStatusEnum;

import java.math.BigDecimal;

public class ProposalResponse {

    private long id;
    private String name;
    private String email;
    private String document;
    private BigDecimal salary;
    private AddressResponse address;
    private ProposalStatusEnum status;
    private CardResponse card;

    @Deprecated
    public ProposalResponse(){}

    public ProposalResponse(Proposal proposal) {
        this.id = proposal.getId();
        this.name = proposal.getName();
        this.email = proposal.getEmail();
        this.document = proposal.getDocument();
        this.salary = proposal.getSalary();
        this.address = new AddressResponse(proposal.getAddress());
        this.status = proposal.getStatus();
        this.card = proposal.getCard() != null ? new CardResponse(proposal.getCard()) : null;
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

    public AddressResponse getAddress() {
        return address;
    }

    public ProposalStatusEnum getStatus() {
        return status;
    }

    public CardResponse getCard() {
        return card;
    }
}
