package com.zup.orange.proposta.entity.biometry;

import com.zup.orange.proposta.entity.card.Card;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "biometry")
public class Biometry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "{NotBlank}")
    private String biometry;
    @NotNull(message = "{NotNull}")
    @ManyToOne
    private Card card;

    @Deprecated
    public Biometry() {
    }

    public Biometry(@NotBlank(message = "{NotBlank}") String biometry, @NotNull(message = "{NotNull}") Card card) {
        Assert.hasLength(biometry, "Invalid Biometry");
        Assert.notNull(card, "Invalid Card");

        this.biometry = biometry;
        this.card = card;
    }

    public long getId() {
        return id;
    }

    public String getBiometry() {
        return biometry;
    }

    public Card getCard() {
        return card;
    }
}
