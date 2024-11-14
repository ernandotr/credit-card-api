package dev.eernandorezende.credit_card_api.infra.repositories;

import dev.eernandorezende.credit_card_api.domain.entities.Flag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlagRepository extends JpaRepository<Flag, Integer> {
}
