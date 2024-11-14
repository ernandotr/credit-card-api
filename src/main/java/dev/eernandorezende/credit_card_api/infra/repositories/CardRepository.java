package dev.eernandorezende.credit_card_api.infra.repositories;

import dev.eernandorezende.credit_card_api.domain.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Integer> {
}
