package dev.eernandorezende.credit_card_api.application.requests;

import java.math.BigDecimal;

public record CardRequest(String name, BigDecimal maxLimit, String number, String holder, String validThru, short cvc) {
}
