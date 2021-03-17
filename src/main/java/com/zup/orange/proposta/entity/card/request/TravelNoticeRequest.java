package com.zup.orange.proposta.entity.card.request;

import com.zup.orange.proposta.entity.card.Card;
import com.zup.orange.proposta.entity.card.Warning;
import org.springframework.util.Assert;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class TravelNoticeRequest {

    @NotBlank(message = "{NotBlank}")
    private String destination;
    @NotNull(message = "{NotNull}")
    @Future
    private LocalDate returnDate;

    @Deprecated
    public TravelNoticeRequest() { }

    public TravelNoticeRequest(String destination, LocalDate returnDate) {
        Assert.hasLength(destination, "The destination cannot be null");
        Assert.notNull(returnDate, "The end date must not be null");

        this.destination = destination;
        this.returnDate = returnDate;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public Warning toModel(Card card, String ip, String system){
        return new Warning(
                this.returnDate,
                this.destination,
                ip,
                system,
                card
        );
    }
}
