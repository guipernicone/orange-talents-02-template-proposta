package com.zup.orange.proposta.entity.proposal;

import com.zup.orange.proposta.client.account.AccountClient;
import com.zup.orange.proposta.client.account.response.AssociateCardDueDateResponse;
import com.zup.orange.proposta.client.account.response.AssociateCardResponse;
import com.zup.orange.proposta.entity.card.Card;
import com.zup.orange.proposta.entity.card.DueDate;
import com.zup.orange.proposta.entity.proposal.schedule.GetCreditCardForApprovedProposal;
import com.zup.orange.proposta.repository.CardRepository;
import com.zup.orange.proposta.repository.ProposalRepository;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@SpringBootTest
@ActiveProfiles("test")
public class GetCreditCardForApprovedProposalTest {

    @Mock
    ProposalRepository mockProposalRepository;

    @Mock
    CardRepository mockCardRepository;

    @Mock
    AccountClient mockAccountClient;

    @InjectMocks
    GetCreditCardForApprovedProposal getCreditCardForApprovedProposal;

    @PersistenceContext
    EntityManager entityManager;

    @Test
    public void testScheduleTaskProposalNotFound(){
        Mockito.when(mockProposalRepository.findByStatusAndCardIsNull(ArgumentMatchers.any())).thenReturn(new ArrayList<>());

        getCreditCardForApprovedProposal.scheduleTask();

        Mockito.verify(mockAccountClient, Mockito.times(0)).associateCard(ArgumentMatchers.any());
        Mockito.verify(mockCardRepository, Mockito.times(0)).save(ArgumentMatchers.any());
    }

    @Test
    @Transactional
    public void testScheduleTaskProposalAssociateCard(){
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
        entityManager.persist(proposal);

        AssociateCardDueDateResponse dueDate = new AssociateCardDueDateResponse(
                "1",
                1,
                LocalDateTime.now()
        );

        AssociateCardResponse cardResponse = new AssociateCardResponse(
                "123",
                LocalDateTime.now(),
                proposal.getName(),
                new ArrayList<>(),
                new ArrayList<>(),
                new BigDecimal(1),
                null,
                dueDate,
                String.valueOf(proposal.getId())
        );

        Mockito.when(mockProposalRepository.findByStatusAndCardIsNull(ArgumentMatchers.any())).thenReturn(Collections.singletonList(proposal));
        Mockito.when(mockAccountClient.associateCard(ArgumentMatchers.any())).thenReturn(cardResponse);

        getCreditCardForApprovedProposal.scheduleTask();

        Mockito.verify(mockAccountClient, Mockito.times(1)).associateCard(ArgumentMatchers.any());
        Mockito.verify(mockCardRepository, Mockito.times(1)).save(ArgumentMatchers.any());
    }

}
