package com.zup.orange.proposta.entity.proposal.request;

import com.zup.orange.proposta.entity.proposal.Address;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;

public class AddressRequest {
    @NotBlank(message = "{NotBlank}")
    private String street;
    @NotBlank(message = "{NotBlank}")
    private String number;
    @NotBlank(message = "{NotBlank}")
    private String neighborhood;
    @NotBlank(message = "{NotBlank}")
    private String city;
    @NotBlank(message = "{NotBlank}")
    private String state;
    @NotBlank(message = "{NotBlank}")
    private String cep;

    @Deprecated
    public AddressRequest() {}

    public AddressRequest(String street, String number, String neighborhood, String city, String state, String cep) {
        Assert.hasLength(street, "The field street cannot be null");
        Assert.hasLength(number, "The field number cannot be null");
        Assert.hasLength(neighborhood, "The field neighborhood cannot be null");
        Assert.hasLength(city, "The field city cannot be null");
        Assert.hasLength(state, "The field state cannot be null");
        Assert.hasLength(cep, "The field street cep be null");

        this.street = street;
        this.number = number;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.cep = cep;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCep() {
        return cep;
    }

    public Address toModel(){
        return new Address(
                street,
                number,
                neighborhood,
                city,
                state,
                cep
        );
    }
}
