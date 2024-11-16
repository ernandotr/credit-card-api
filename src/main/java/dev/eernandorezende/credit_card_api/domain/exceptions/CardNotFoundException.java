package dev.eernandorezende.credit_card_api.domain.exceptions;

public class CardNotFoundException extends RuntimeException {
    public CardNotFoundException() {
        super("Credit card not found. ");
    }
}
