package com.zup.orange.proposta.entity.proposal.response;

import com.zup.orange.proposta.entity.proposal.Address;

import javax.validation.constraints.NotBlank;

public class AddressResponse {

    private String street;
    private String number;
    private String neighborhood;
    private String city;
    private String state;
    private String cep;

    @Deprecated
    public AddressResponse() {}

    public AddressResponse(Address address) {
        this.street = address.getStreet();
        this.number = address.getNumber();
        this.neighborhood = address.getNeighborhood();
        this.city = address.getCity();
        this.state = address.getState();
        this.cep = address.getCep();
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
}
