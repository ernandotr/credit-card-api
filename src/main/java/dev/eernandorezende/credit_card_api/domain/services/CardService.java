package dev.eernandorezende.credit_card_api.domain.services;

import dev.eernandorezende.credit_card_api.application.requests.CardRequest;
import dev.eernandorezende.credit_card_api.application.responses.CardResponse;
import dev.eernandorezende.credit_card_api.domain.entities.Card;
import dev.eernandorezende.credit_card_api.domain.exceptions.CardNotFoundException;
import dev.eernandorezende.credit_card_api.domain.exceptions.FlagNotFoundException;
import dev.eernandorezende.credit_card_api.domain.exceptions.UserNotFoundException;
import dev.eernandorezende.credit_card_api.infra.repositories.CardRepository;
import dev.eernandorezende.credit_card_api.infra.repositories.FlagRepository;
import dev.eernandorezende.credit_card_api.infra.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CardService {

    private  final CardRepository cardRepository;
    private final FlagRepository flagRepository;
    private final UserRepository userRepository;

    public List<CardResponse> getAll() {
        return cardRepository.findAll().stream().map( this::toResponse).toList();
    }

    public CardResponse getById(Long id) {
        return cardRepository.findById(id).map(this::toResponse).orElseThrow(CardNotFoundException::new);
    }
    public CardResponse create(CardRequest request) {
        var card = toEntity(request);

        card = cardRepository.save(card);
        return toResponse(card);
    }

    public void update(Long id, CardRequest request) {
        var entity = cardRepository.findById(id).orElseThrow(CardNotFoundException::new);

        entity.setCardName(request.name());
        entity.setNumber(request.number());
        entity.setHolderName(request.holderName());
        entity.setValidThru(request.validThru());
        entity.setCvc(request.cvc());
        entity.setTotalLimit(request.maxLimit());
        entity.setInvoiceDueDay(request.invoiceDueDay());

        entity = cardRepository.save(entity);

    }

    public void delete(Long id) {
        cardRepository.deleteById(id);
    }

    private CardResponse toResponse(Card card) {
        return new CardResponse(card.getId(), card.getCardName(), card.getNumber(), card.getHolderName(), card.getValidThru(), card.getCvc());
    }

    private Card toEntity(CardRequest request) {
        var index = request.number().length() - 4;
        var flag = flagRepository.findById(request.flagId()).orElseThrow(FlagNotFoundException::new);
        var user = userRepository.findById(request.userId()).orElseThrow(UserNotFoundException::new);
        return Card.builder()
                .cardName(request.name())
                .number(request.number())
                .holderName(request.holderName())
                .lastDigits(request.number().substring(index))
                .cvc(request.cvc())
                .validThru(request.validThru())
                .totalLimit(request.maxLimit())
                .usedLimit(BigDecimal.ZERO)
                .user(user)
                .flag(flag)
                .build();

    }
}
