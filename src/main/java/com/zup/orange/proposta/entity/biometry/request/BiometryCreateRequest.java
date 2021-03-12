package com.zup.orange.proposta.entity.biometry.request;

import com.zup.orange.proposta.entity.biometry.Biometry;
import com.zup.orange.proposta.entity.card.Card;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

public class BiometryCreateRequest {

    @NotBlank(message = "{NotBlank}")
    public String biometry;

    @Deprecated
    public BiometryCreateRequest() {
    }

    public BiometryCreateRequest(@NotBlank(message = "{NotBlank}") String biometry) {
        this.biometry = biometry;
    }

    public String getBiometry() {
        return biometry;
    }

    public Biometry toModel(Card card){
        return new Biometry(biometry, card);
    }

}
