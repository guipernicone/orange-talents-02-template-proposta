package com.zup.orange.proposta.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zup.orange.proposta.entity.proposal.request.AddressRequest;
import com.zup.orange.proposta.entity.proposal.request.CreateProposalRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProposalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @PersistenceContext
    private EntityManager entityManager;

    private CreateProposalRequest proposalRequest;

    @BeforeEach
    public void setup(){

        AddressRequest addressRequest = new AddressRequest(
                "Rua",
                "99",
                "Dom Pedro",
                "city",
                "state",
                "123456"
        );

        this.proposalRequest = new CreateProposalRequest(
                "Name",
                "email@email.com",
                "40876816880",
                new BigDecimal(5000),
                addressRequest
        );
    }

    @Test
    @Transactional
    public void testCreateProposal() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/proposal")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(this.proposalRequest))
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @Transactional
    public void testCreateProposalInvalidDocument() throws Exception {
        entityManager.persist(proposalRequest.toModel());

        ObjectMapper objectMapper = new ObjectMapper();
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/proposal")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(this.proposalRequest))
        ).andExpect(MockMvcResultMatchers.status().is(422));
    }
}
