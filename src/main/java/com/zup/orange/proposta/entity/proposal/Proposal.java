package com.zup.orange.proposta.entity.proposal;

import com.zup.orange.proposta.client.analyze.AnalyzeClient;
import com.zup.orange.proposta.client.analyze.request.AnalyzeRequest;
import com.zup.orange.proposta.client.analyze.response.AnalyzeResponse;
import com.zup.orange.proposta.entity.card.Card;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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
    @Column(unique = true)
    private String document;
    @NotNull(message = "{NotNull}")
    @Positive
    private BigDecimal salary;
    @NotNull(message = "{NotNull}")
    @Embedded
    private Address address;
    @Enumerated(EnumType.STRING)
    private ProposalStatusEnum status;
    @OneToOne(mappedBy = "proposal", cascade = CascadeType.MERGE)
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

    public void updateStatus(AnalyzeClient client){
        try{
            AnalyzeResponse response = client.analyse(new AnalyzeRequest(this));
            this.status = response.getResult().getValue();
        }
        catch (FeignException e) {
            this.status = ProposalStatusEnum.NAO_ELEGIVEL;
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error happened");
        }

    }

    public void updateCard(Card card){
        this.card = card;
    }
}
