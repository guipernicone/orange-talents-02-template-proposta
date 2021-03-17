package com.zup.orange.proposta.entity.card;

import com.zup.orange.proposta.client.account.AccountClient;
import com.zup.orange.proposta.client.account.response.WalletCardResponse;
import com.zup.orange.proposta.entity.card.enums.WalletEnum;
import com.zup.orange.proposta.entity.card.enums.WalletStatusEnum;
import com.zup.orange.proposta.entity.proposal.Address;
import com.zup.orange.proposta.entity.proposal.Proposal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootTest
public class WalletTest {

    @Mock
    AccountClient accountClient;

    private Wallet testWallet;

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

        Card card = new Card(
                "123",
                LocalDateTime.now(),
                proposal.getName(),
                null,
                new BigDecimal(1),
                null,
                null,
                proposal
        );

        testWallet = new Wallet(
                WalletEnum.SAMSUNGPAY,
                "guilherme.pernicone@zup.com.br",
                "issuerTest",
                card
        );
    }

    @Test
    public void testAssociateCardWallet(){
        WalletCardResponse walletCardResponse = new WalletCardResponse(WalletStatusEnum.ASSOCIADA,"id");
        Mockito.when(accountClient.walletCard(ArgumentMatchers.anyString(), ArgumentMatchers.any())).thenReturn(walletCardResponse);

        boolean response = testWallet.associateCardWallet(accountClient);

        Assertions.assertTrue(response);
        Assertions.assertEquals("id", testWallet.getWalletId());
    }

    @Test
    public void testAssociateCardWalletFalse(){
        WalletCardResponse walletCardResponse = new WalletCardResponse(WalletStatusEnum.FALHA,"id");
        Mockito.when(accountClient.walletCard(ArgumentMatchers.anyString(), ArgumentMatchers.any())).thenReturn(walletCardResponse);

        boolean response = testWallet.associateCardWallet(accountClient);

        Assertions.assertFalse(response);
        Assertions.assertNotEquals("id", testWallet.getWalletId());
    }

    @Test
    public void testAssociateCardWalletException(){
        WalletCardResponse walletCardResponse = new WalletCardResponse(WalletStatusEnum.FALHA,"id");
        Mockito.when(accountClient.walletCard(ArgumentMatchers.anyString(), ArgumentMatchers.any())).thenThrow(new RuntimeException());

        ResponseStatusException exception = Assertions.assertThrows(
                ResponseStatusException.class,
                () -> testWallet.associateCardWallet(accountClient)
        );

        Assertions.assertEquals(
                "500 INTERNAL_SERVER_ERROR \"An unexpected error occurred\"",
                exception.getMessage()
        );
    }

}
