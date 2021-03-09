package com.zup.orange.proposta.client.AnalyzeClient.response;

import com.zup.orange.proposta.entity.proposal.ProposalStatusEnum;

public enum AnalyseStatusEnum {
    COM_RESTRICAO(ProposalStatusEnum.NAO_ELEGIVEL),
    SEM_RESTRICAO(ProposalStatusEnum.ELEGIVEL);

    private ProposalStatusEnum value;

    private AnalyseStatusEnum(ProposalStatusEnum value) {
        this.value = value;
    }

    public ProposalStatusEnum getValue() {
        return value;
    }
}
