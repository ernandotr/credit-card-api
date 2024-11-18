package dev.eernandorezende.credit_card_api.infra.repositories;

import dev.eernandorezende.credit_card_api.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
