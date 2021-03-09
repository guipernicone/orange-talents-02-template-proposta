package com.zup.orange.proposta.repository;

import com.zup.orange.proposta.entity.proposal.Proposal;
import com.zup.orange.proposta.entity.proposal.ProposalStatusEnum;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProposalRepository extends CrudRepository<Proposal, Long> {

    Boolean existsByDocument(String document);

    List<Proposal> findByStatusAndCardIsNull(ProposalStatusEnum status);

}
