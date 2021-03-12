package com.zup.orange.proposta.controller;

import com.zup.orange.proposta.entity.biometry.Biometry;
import com.zup.orange.proposta.entity.biometry.request.BiometryCreateRequest;
import com.zup.orange.proposta.entity.card.Card;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/card")
public class CardController {

    @PersistenceContext
    EntityManager entityManager;

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

}
