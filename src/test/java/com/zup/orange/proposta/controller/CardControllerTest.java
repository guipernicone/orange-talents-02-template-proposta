package com.zup.orange.proposta.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zup.orange.proposta.client.account.AccountClient;
import com.zup.orange.proposta.client.account.request.WarnCardRequest;
import com.zup.orange.proposta.client.account.response.CardBlockResponse;
import com.zup.orange.proposta.client.account.response.WarnCardResponse;
import com.zup.orange.proposta.entity.biometry.request.BiometryCreateRequest;
import com.zup.orange.proposta.entity.card.Card;
import com.zup.orange.proposta.entity.card.Warning;
import com.zup.orange.proposta.entity.card.enums.CardStatusEnum;
import com.zup.orange.proposta.entity.card.enums.WarnStatusEnum;
import com.zup.orange.proposta.entity.card.request.TravelNoticeRequest;
import com.zup.orange.proposta.entity.proposal.Address;
import com.zup.orange.proposta.entity.proposal.Proposal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @PersistenceContext
    private EntityManager entityManager;

    @MockBean
    private AccountClient accountClient;

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

        entityManager.persist(proposal);

        this.cardTest = new Card(
                "123",
                LocalDateTime.now(),
                proposal.getName(),
                null,
                null,
                new BigDecimal(1),
                null,
                null,
                proposal
        );

    }

    @Test
    @Transactional
    @WithMockUser
    public void testAddBiometry() throws Exception {
        BiometryCreateRequest request = new BiometryCreateRequest("asasa");
        entityManager.persist(cardTest);

        ObjectMapper objectMapper = new ObjectMapper();
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/card/{id}/biometry", cardTest.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @Transactional
    @WithMockUser
    public void testAddBiometryBadRequest() throws Exception {
        BiometryCreateRequest request = new BiometryCreateRequest("");
        entityManager.persist(cardTest);

        ObjectMapper objectMapper = new ObjectMapper();
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/card/{id}/biometry", cardTest.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @Transactional
    @WithMockUser
    public void testAddBiometryNotFound() throws Exception {
        BiometryCreateRequest request = new BiometryCreateRequest("12");

        ObjectMapper objectMapper = new ObjectMapper();
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/card/{id}/biometry", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @Transactional
    @WithMockUser
    public void testBlockCardNotFound() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/card/{id}/block", "1")
                        .header(HttpHeaders.USER_AGENT, "testAgent")
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @Transactional
    @WithMockUser
    public void testBlockCardBadRequestBlockedCard() throws Exception {
        String agentHeader = "testAgent";
        CardBlockResponse blockResponse =  new CardBlockResponse(CardStatusEnum.BLOQUEADO);
        Mockito.when(accountClient.blockCard(ArgumentMatchers.anyString(), ArgumentMatchers.any())).thenReturn(blockResponse);
        cardTest.blockCard(accountClient, agentHeader);

        entityManager.persist(cardTest);

        this.mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/card/{id}/block", cardTest.getCardNumber())
                                .header(HttpHeaders.USER_AGENT, agentHeader)
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Card already blocked"));
    }

    @Test
    @Transactional
    @WithMockUser
    public void testBlockCardExternalApiBadRequestBlockedCard() throws Exception {
        String agentHeader = "testAgent";
        CardBlockResponse blockResponse =  new CardBlockResponse(CardStatusEnum.FALHA);
        Mockito.when(accountClient.blockCard(ArgumentMatchers.anyString(), ArgumentMatchers.any())).thenReturn(blockResponse);

        entityManager.persist(cardTest);

        this.mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/card/{id}/block", cardTest.getCardNumber())
                                .header(HttpHeaders.USER_AGENT, agentHeader)
                )
                .andExpect(MockMvcResultMatchers.status().is(422));
    }

    @Test
    @Transactional
    @WithMockUser
    public void testBlockCardBlockedCard() throws Exception {
        String agentHeader = "testAgent";
        CardBlockResponse blockResponse =  new CardBlockResponse(CardStatusEnum.BLOQUEADO);
        Mockito.when(accountClient.blockCard(ArgumentMatchers.anyString(), ArgumentMatchers.any())).thenReturn(blockResponse);

        entityManager.persist(cardTest);

        this.mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/card/{id}/block", cardTest.getCardNumber())
                                .header(HttpHeaders.USER_AGENT, agentHeader)
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
        Card card = entityManager.find(Card.class, cardTest.getId());
        Assertions.assertEquals(CardStatusEnum.BLOQUEADO, card.getStatus());
    }

    @Test
    @Transactional
    @WithMockUser
    public void testTravelNoticeBadRequest() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/card/{id}/warning", "1")
                        .header(HttpHeaders.USER_AGENT, "testAgent")
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @Transactional
    @WithMockUser
    public void testTravelNoticeNotFound() throws Exception {
        System.out.println();
        String request = "{\n" +
                "    \"destination\": \"rome\",\n" +
                "    \"returnDate\": \"" + LocalDate.now().plusDays(1).toString() + "\"\n" +
                "}";

        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/card/{id}/warning", "1")
                        .header(HttpHeaders.USER_AGENT, "testAgent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @Transactional
    @WithMockUser
    public void testTravelNoticeBadRequestExternalApi() throws Exception {
        WarnCardResponse warnCardResponse = new WarnCardResponse(WarnStatusEnum.FALHA);
        Mockito.when(accountClient.warnCard(ArgumentMatchers.anyString(), ArgumentMatchers.any())).thenReturn(warnCardResponse);
        entityManager.persist(this.cardTest);
        String request = "{\n" +
                "    \"destination\": \"rome\",\n" +
                "    \"returnDate\": \"" + LocalDate.now().plusDays(1).toString() + "\"\n" +
                "}";

        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/card/{id}/warning", this.cardTest.getCardNumber())
                        .header(HttpHeaders.USER_AGENT, "testAgent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @Transactional
    @WithMockUser
    public void testTravelNoticeInternealServerErrorExternalApi() throws Exception {
        WarnCardResponse warnCardResponse = new WarnCardResponse(WarnStatusEnum.FALHA);
        Mockito.when(accountClient.warnCard(ArgumentMatchers.anyString(), ArgumentMatchers.any())).thenThrow(new RuntimeException());
        entityManager.persist(this.cardTest);
        String request = "{\n" +
                "    \"destination\": \"rome\",\n" +
                "    \"returnDate\": \"" + LocalDate.now().plusDays(1).toString() + "\"\n" +
                "}";

        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/card/{id}/warning", this.cardTest.getCardNumber())
                        .header(HttpHeaders.USER_AGENT, "testAgent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request)
                )
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.content().string("An unexpected error occurred"));
    }

    @Test
    @Transactional
    @WithMockUser
    public void testTravelNotice() throws Exception {
        WarnCardResponse warnCardResponse = new WarnCardResponse(WarnStatusEnum.CRIADO);
        Mockito.when(accountClient.warnCard(ArgumentMatchers.anyString(), ArgumentMatchers.any())).thenReturn(warnCardResponse);
        entityManager.persist(this.cardTest);
        String request = "{\n" +
                "    \"destination\": \"rome\",\n" +
                "    \"returnDate\": \"" + LocalDate.now().plusDays(1).toString() + "\"\n" +
                "}";

        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/card/{id}/warning", this.cardTest.getCardNumber())
                        .header(HttpHeaders.USER_AGENT, "testAgent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request)
        )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
