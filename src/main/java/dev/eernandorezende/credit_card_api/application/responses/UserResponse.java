package dev.eernandorezende.credit_card_api.application.responses;

import java.util.Set;

public record UserResponse(Long id, String name, String email, Set<String> roles) {
}
