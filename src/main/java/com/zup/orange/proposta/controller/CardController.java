package com.zup.orange.proposta.controller;

import com.zup.orange.proposta.client.account.AccountClient;
import com.zup.orange.proposta.client.account.request.WarnCardRequest;
import com.zup.orange.proposta.client.account.response.CardBlockResponse;
import com.zup.orange.proposta.client.account.response.WarnCardResponse;
import com.zup.orange.proposta.entity.biometry.Biometry;
import com.zup.orange.proposta.entity.biometry.request.BiometryCreateRequest;
import com.zup.orange.proposta.entity.card.Blocked;
import com.zup.orange.proposta.entity.card.Card;
import com.zup.orange.proposta.entity.card.Warning;
import com.zup.orange.proposta.entity.card.enums.CardStatusEnum;
import com.zup.orange.proposta.entity.card.enums.WarnStatusEnum;
import com.zup.orange.proposta.entity.card.request.TravelNoticeRequest;
import com.zup.orange.proposta.repository.CardRepository;
import feign.FeignException;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/card")
@Timed
public class CardController {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    AccountClient accountClient;

    @PostMapping("/{id}/biometry")
    @Transactional
    public ResponseEntity<Void> createBiometry(
            @PathVariable long id,
            @RequestBody @Valid BiometryCreateRequest biometryCreateRequest,
            UriComponentsBuilder uriComponentsBuilder
    ){
        Card card = entityManager.find(Card.class, id);

        if(card == null){
            return ResponseEntity.notFound().build();
        }
        Biometry biometry = biometryCreateRequest.toModel(card);
        entityManager.persist(biometry);

        URI uri = uriComponentsBuilder.path("/biometry/{id}").buildAndExpand(biometry.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/{card_number}/block")
    @Transactional
    public ResponseEntity<?> blockCard(
            @PathVariable(value = "card_number") String cardNumber,
            @RequestHeader(HttpHeaders.USER_AGENT) String userAgent,
            HttpServletRequest request
    ){
        Optional<Card> cardOptional = cardRepository.findByCardNumber(cardNumber);

        if (cardOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Card card = cardOptional.get();

        if (card.getStatus() == CardStatusEnum.BLOQUEADO){
            return ResponseEntity.badRequest().body("Card already blocked");
        }

        Blocked blocked = new Blocked(LocalDateTime.now(), userAgent, request.getRemoteAddr(), card);
        entityManager.persist(blocked);

        if(!card.blockCard(accountClient, blocked.getResponsibleSystem())) {
            return ResponseEntity.status(422).build();
        }

        entityManager.persist(card);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{card_number}/warning")
    @Transactional
    public ResponseEntity<?> travelNotice(
            @PathVariable(value = "card_number") String cardNumber,
            @RequestBody @Valid TravelNoticeRequest travelRequest,
            @RequestHeader(HttpHeaders.USER_AGENT) String userAgent,
            HttpServletRequest request
    ){

        Optional<Card> cardOptional = cardRepository.findByCardNumber(cardNumber);
        System.out.println(cardOptional.isEmpty());
        if (cardOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Card card = cardOptional.get();
        Warning warning = travelRequest.toModel(card, request.getRemoteAddr(), userAgent);
        WarnCardRequest warnCardRequest = new WarnCardRequest(warning.getDestination(), warning.getEndDate());

        try{
            WarnCardResponse response = accountClient.warnCard(card.getCardNumber(),warnCardRequest);
            if (response.getResult() == WarnStatusEnum.FALHA){
                return ResponseEntity.badRequest().build();
            }
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }

        entityManager.persist(warning);
        return ResponseEntity.ok().build();
    }

}
