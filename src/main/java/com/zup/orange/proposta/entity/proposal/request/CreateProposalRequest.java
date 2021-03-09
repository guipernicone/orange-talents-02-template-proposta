package com.zup.orange.proposta.entity.proposal.request;

import com.zup.orange.proposta.entity.proposal.Proposal;
import com.zup.orange.proposta.validation.CPFOrCNPJ;
import org.springframework.util.Assert;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class CreateProposalRequest {

    @NotBlank(message = "{NotBlank}")
    private String name;
    @NotBlank(message = "{NotBlank}")
    @Email
    private String email;
    @NotBlank(message = "{NotBlank}")
    @CPFOrCNPJ
    private String document;
    @NotNull(message = "{NotNull}")
    @Positive
    private BigDecimal salary;
    @NotNull(message = "{NotNull}")
    private AddressRequest address;

    @Deprecated
    public CreateProposalRequest() {}

    public CreateProposalRequest(String name, String email, String document, BigDecimal salary, AddressRequest address) {
        Assert.hasLength(name, "The name must not be empty");
        Assert.hasLength(email, "The email must not be empty");
        Assert.hasLength(document, "The document must not be empty");
        Assert.notNull(salary, "The salary must not be empty");
        Assert.notNull(address, "The address must not be empty");
        this.name = name;
        this.email = email;
        this.document = document;
        this.salary = salary;
        this.address = address;
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

    public AddressRequest getAddress() {
        return address;
    }

    public Proposal toModel(){
        return new Proposal(
                this.name,
                this.email,
                this.document,
                this.salary,
                this.address.toModel()
        );
    }
}
