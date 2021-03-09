package com.zup.orange.proposta.entity.proposal;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Embeddable
public class Address {

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
    public Address() {
    }

    public Address(
            @NotBlank(message = "{NotBlank}") String street,
            @NotBlank(message = "{NotBlank}") String number,
            @NotBlank(message = "{NotBlank}") String neighborhood,
            @NotBlank(message = "{NotBlank}") String city,
            @NotBlank(message = "{NotBlank}") String state,
            @NotBlank(message = "{NotBlank}") String cep
    ) {
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
}
