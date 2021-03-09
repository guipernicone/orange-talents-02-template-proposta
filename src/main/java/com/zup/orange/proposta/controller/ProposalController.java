package com.zup.orange.proposta.controller;

import com.zup.orange.proposta.client.AnalyzeClient.AnalyzeClient;
import com.zup.orange.proposta.client.AnalyzeClient.request.AnalyzeRequest;
import com.zup.orange.proposta.client.AnalyzeClient.response.AnalyzeResponse;
import com.zup.orange.proposta.entity.proposal.Proposal;
import com.zup.orange.proposta.entity.proposal.request.CreateProposalRequest;
import com.zup.orange.proposta.repository.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/proposal")
public class ProposalController {

    @Autowired
    ProposalRepository proposalRepository;

    @Autowired
    AnalyzeClient analyzeClient;

    @PostMapping
    @Transactional
    public ResponseEntity<?> createProposal(@RequestBody @Valid CreateProposalRequest createProposalRequest, UriComponentsBuilder uriComponentsBuilder){
        Proposal proposal = createProposalRequest.toModel();

        if (proposalRepository.existsByDocument(proposal.getDocument())){
            return ResponseEntity.status(HttpStatus.valueOf(422)).build();
        }
        proposalRepository.save(proposal);

        AnalyzeResponse response = analyzeClient.analyse(new AnalyzeRequest(proposal));
        proposal.updateStatus(response);

        proposalRepository.save(proposal);

        URI uri = uriComponentsBuilder.path("/{id}").buildAndExpand(proposal.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


}
