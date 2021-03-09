package com.zup.orange.proposta.entity.proposal;

import com.zup.orange.proposta.repository.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetCreditCardForApprovedProposal {
    @Autowired
    ProposalRepository proposalRepository;

//    @Scheduled(fixedDelay = 5000)
    private void scheduleTask() {
        List<Proposal> proposalList = proposalRepository.findByStatusAndCardIsNull(ProposalStatusEnum.ELEGIVEL);
        System.out.println(proposalList.size());
    }
}
