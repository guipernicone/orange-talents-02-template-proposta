package com.zup.orange.proposta.entity.card;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zup.orange.proposta.client.account.AccountClient;
import com.zup.orange.proposta.client.account.request.WalletCardRequest;
import com.zup.orange.proposta.client.account.response.WalletCardResponse;
import com.zup.orange.proposta.entity.card.enums.WalletEnum;
import com.zup.orange.proposta.entity.card.enums.WalletStatusEnum;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "wallet")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String walletId;
    @NotNull
    @Enumerated(EnumType.STRING)
    private WalletEnum wallet;
    @Email
    private String email;
    @NotNull(message = "{NotNull}")
    private LocalDateTime associateIn;
    @NotBlank(message = "{NotBlank}")
    private String issuer;
    @ManyToOne
    private Card card;

    @Deprecated
    public Wallet() {
    }

    public Wallet(
            @NotNull WalletEnum wallet,
            @Email String email,
            @NotBlank(message = "{NotBlank}") String issuer,
            Card card
    ) {
        this.wallet = wallet;
        this.email = email;
        this.associateIn = LocalDateTime.now();
        this.issuer = issuer;
        this.card = card;
    }

    public long getId() {
        return id;
    }

    public String getWalletId() {
        return walletId;
    }

    public WalletEnum getWallet() {
        return wallet;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getAssociateIn() {
        return associateIn;
    }

    public String getIssuer() {
        return issuer;
    }

    public Card getCard() {
        return card;
    }

    public boolean associateCardWallet(AccountClient accountClient) {
        try{
            WalletCardRequest walletCardRequest = new WalletCardRequest(this.email, this.wallet);
            WalletCardResponse walletCardResponse = accountClient.walletCard(this.card.getCardNumber(), walletCardRequest);
            if (walletCardResponse.getResultado() == WalletStatusEnum.FALHA)
            {
                return false;
            }
            this.walletId = walletCardResponse.getId();
            return true;
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
        }
    }
}
