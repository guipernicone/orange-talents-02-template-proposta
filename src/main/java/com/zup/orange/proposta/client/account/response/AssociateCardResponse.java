package com.zup.orange.proposta.client.account.response;

import com.zup.orange.proposta.entity.card.*;
import com.zup.orange.proposta.entity.proposal.Proposal;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class AssociateCardResponse {

    @NotBlank(message = "{NotBlank}")
    private String id;

    @NotNull(message = "{NotNull}")
    private LocalDateTime emitidoEm;

    @NotBlank(message = "{NotBlank}")
    private String titular;

//    @NotNull(message = "{NotNull}")
    private List<AssociateCardInstallmentResponse>  parcelas;

    @NotNull(message = "{NotNull}")
    @Positive
    private BigDecimal limite;

//    @NotNull(message = "{NotNull}")
    private AssociateCardRenegotiationResponse renegociacao;

//    @NotNull(message = "{NotNull}")
    private AssociateCardDueDateResponse vencimento;

    @NotBlank(message = "{NotBlank}")
    private String idProposta;

    @Deprecated
    public AssociateCardResponse() {
    }

    public AssociateCardResponse(
            @NotBlank(message = "{NotBlank}") String id,
            @NotNull(message = "{NotNull}") LocalDateTime emitidoEm,
            @NotBlank(message = "{NotBlank}") String titular,
            List<AssociateCardInstallmentResponse> parcelas,
            @NotNull(message = "{NotNull}") @Positive BigDecimal limite,
            AssociateCardRenegotiationResponse renegociacao,
            AssociateCardDueDateResponse vencimento,
            @NotBlank(message = "{NotBlank}") String idProposta
    ) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.parcelas = parcelas;
        this.limite = limite;
        this.renegociacao = renegociacao;
        this.vencimento = vencimento;
        this.idProposta = idProposta;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public List<AssociateCardInstallmentResponse> getParcelas() {
        return parcelas;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public AssociateCardRenegotiationResponse getRenegociacao() {
        return renegociacao;
    }

    public AssociateCardDueDateResponse getVencimento() {
        return vencimento;
    }

    public String getIdProposta() {
        return idProposta;
    }

    public Card toModel(Proposal proposal){

        Renegotiation renegotiation = this.renegociacao != null ? renegociacao.toModel() : null;

        return new Card(
                this.id,
                this.emitidoEm,
                this.titular,
                this.parcelas
                        .stream()
                        .map(AssociateCardInstallmentResponse::toModel)
                        .collect(Collectors.toList()),
                this.limite,
                renegotiation,
                this.vencimento.toModel(),
                proposal
        );
    }
}
