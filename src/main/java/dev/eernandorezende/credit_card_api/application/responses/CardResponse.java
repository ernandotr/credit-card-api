package dev.eernandorezende.credit_card_api.application.responses;

public record CardResponse(Long id,  String CardName, String number, String holderName, String validThru, Short cvc) {
}
