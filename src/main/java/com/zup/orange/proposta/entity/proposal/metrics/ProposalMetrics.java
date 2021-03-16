package com.zup.orange.proposta.entity.proposal.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class ProposalMetrics {

    private MeterRegistry meterRegistry;
    private Counter createdProposalCounter;

    public ProposalMetrics(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        createdProposalCounter = this.meterRegistry.counter("propostas_criadas");
    }

    public void increment(){
        createdProposalCounter.increment();
    }
}
