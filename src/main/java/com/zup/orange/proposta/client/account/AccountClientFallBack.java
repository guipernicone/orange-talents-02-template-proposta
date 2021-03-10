package com.zup.orange.proposta.client.account;

import com.zup.orange.proposta.client.account.request.AssociateCardRequest;
import com.zup.orange.proposta.client.account.response.AssociateCardResponse;
import org.springframework.stereotype.Component;

@Component
public class AccountClientFallBack implements AccountClient  {

    public AssociateCardResponse associateCard(AssociateCardRequest cardRequest){
        return null;
    }

}
