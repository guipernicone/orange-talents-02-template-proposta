package com.zup.orange.proposta.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zup.orange.proposta.entity.biometry.Biometry;
import com.zup.orange.proposta.entity.biometry.request.BiometryCreateRequest;
import com.zup.orange.proposta.entity.card.Card;
import com.zup.orange.proposta.entity.proposal.Address;
import com.zup.orange.proposta.entity.proposal.Proposal;
import com.zup.orange.proposta.entity.proposal.request.AddressRequest;
import com.zup.orange.proposta.entity.proposal.request.CreateProposalRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @PersistenceContext
    private EntityManager entityManager;

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
}
