package com.zup.orange.proposta.entity.card;

import com.zup.orange.proposta.client.account.AccountClient;
import com.zup.orange.proposta.client.account.response.CardBlockResponse;
import com.zup.orange.proposta.entity.card.enums.CardStatusEnum;
import com.zup.orange.proposta.entity.proposal.Address;
import com.zup.orange.proposta.entity.proposal.Proposal;
import feign.FeignException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootTest
@Profile("test")
public class CardTest {

    @Mock
    private AccountClient mockAccountClient;

    private Card cardTest;

    @BeforeEach
    public void setup(){
        Address address = new Address(
                "Rua",
                "99",
                "Dom Pedro",
                "city",
                "state",
                "123456"
        );

        Proposal proposal = new Proposal(
                "Name",
                "email@email.com",
                "40876816880",
                new BigDecimal(5000),
                address
        );

        this.cardTest = new Card(
                "123",
                LocalDateTime.now(),
                proposal.getName(),
                null,
                new BigDecimal(1),
                null,
                null,
                proposal
        );
    }

    @Test
    public void blockCardTestFeignException(){
        Mockito.
                when(mockAccountClient.blockCard(ArgumentMatchers.anyString(), ArgumentMatchers.any()))
                .thenThrow(FeignException.class);

        boolean response = this.cardTest.blockCard(mockAccountClient, "test");
        Assertions.assertFalse(response);
    }

    @Test
    public void blockCardTestException(){
        Mockito.
                when(mockAccountClient.blockCard(ArgumentMatchers.anyString(), ArgumentMatchers.any()))
                .thenThrow(RuntimeException.class);

        ResponseStatusException response = Assertions.assertThrows(
                ResponseStatusException.class,
                ()-> this.cardTest.blockCard(mockAccountClient, "test")
        );

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatus());
        Assertions.assertEquals("500 INTERNAL_SERVER_ERROR \"An unexpected error happened\"", response.getMessage());
    }

    @Test
    public void blockCardTestFail(){
        Mockito
                .when(mockAccountClient.blockCard(ArgumentMatchers.anyString(), ArgumentMatchers.any()))
                .thenReturn(new CardBlockResponse(CardStatusEnum.FALHA));

        boolean response = this.cardTest.blockCard(mockAccountClient, "test");
        Assertions.assertFalse(response);
    }

    @Test
    public void blockCardTestTrue(){
        Mockito
                .when(mockAccountClient.blockCard(ArgumentMatchers.anyString(), ArgumentMatchers.any()))
                .thenReturn(new CardBlockResponse(CardStatusEnum.BLOQUEADO));

        boolean response = this.cardTest.blockCard(mockAccountClient, "test");
        Assertions.assertTrue(response);
    }
}