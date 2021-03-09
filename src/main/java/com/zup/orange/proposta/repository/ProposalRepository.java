package com.zup.orange.proposta.repository;

import com.zup.orange.proposta.entity.proposal.Proposal;
import org.springframework.data.repository.CrudRepository;

public interface ProposalRepository extends CrudRepository<Proposal, Long> {

    Boolean existsByDocument(String document);

}
