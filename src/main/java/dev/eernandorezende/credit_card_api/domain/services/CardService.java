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
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final FlagRepository flagRepository;
    private final UserRepository userRepository;

    public CardService(CardRepository cardRepository, FlagRepository flagRepository, UserRepository userRepository) {
        this.cardRepository = cardRepository;
        this.flagRepository = flagRepository;
        this.userRepository = userRepository;
    }

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

        cardRepository.save(entity);

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
        Card card = new Card();
        card.setCardName(request.name());
        card.setNumber(request.number());
        card.setHolderName(request.holderName());
        card.setLastDigits(request.number().substring(index));
        card.setCvc(request.cvc());
        card.setValidThru(request.validThru());
        card.setTotalLimit(request.maxLimit());
        card.setUsedLimit(BigDecimal.ZERO);
        card.setUser(user);
        card.setFlag(flag);
        return card;
    }
}
