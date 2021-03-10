package com.zup.orange.proposta.entity.proposal.schedule;

import com.zup.orange.proposta.client.account.AccountClient;
import com.zup.orange.proposta.client.account.request.AssociateCardRequest;
import com.zup.orange.proposta.client.account.response.AssociateCardResponse;
import com.zup.orange.proposta.entity.card.Card;
import com.zup.orange.proposta.entity.proposal.Proposal;
import com.zup.orange.proposta.entity.proposal.ProposalStatusEnum;
import com.zup.orange.proposta.repository.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetCreditCardForApprovedProposal {
    @Autowired
    ProposalRepository proposalRepository;

    @Autowired
    AccountClient accountClient;

    @Scheduled(fixedDelay = 60000)
    private void scheduleTask() {
        List<Proposal> proposalList = proposalRepository.findByStatusAndCardIsNull(ProposalStatusEnum.ELEGIVEL);

        proposalList.forEach(proposal -> {
            AssociateCardRequest cardRequest = new AssociateCardRequest(
                    proposal.getDocument(),
                    proposal.getName(),
                    String.valueOf(proposal.getId())
            );
            AssociateCardResponse cardResponse = accountClient.associateCard(cardRequest);
            Card card = cardResponse.toModel(proposal);
            proposal.updateCard(card);
            proposalRepository.save(proposal);
        });
    }
}
