package com.zup.orange.proposta.repository;

import com.zup.orange.proposta.entity.card.Card;
import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CrudRepository<Card, Long> {
}
