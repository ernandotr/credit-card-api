package dev.eernandorezende.credit_card_api.infra.repositories;

import dev.eernandorezende.credit_card_api.domain.entities.Holder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HolderRepository extends JpaRepository<Holder, Long> {
}
