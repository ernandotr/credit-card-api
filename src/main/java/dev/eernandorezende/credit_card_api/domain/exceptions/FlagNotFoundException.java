package dev.eernandorezende.credit_card_api.domain.exceptions;

public class FlagNotFoundException extends RuntimeException{
    public FlagNotFoundException() {
        super("Flag not found.");
    }

    public FlagNotFoundException(String message) {
        super(message);
    }
}
