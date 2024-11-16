package dev.eernandorezende.credit_card_api.domain.services;

import dev.eernandorezende.credit_card_api.application.requests.FlagRequest;
import dev.eernandorezende.credit_card_api.application.responses.FlagResponse;
import dev.eernandorezende.credit_card_api.domain.entities.Flag;
import dev.eernandorezende.credit_card_api.domain.exceptions.FlagNotFoundException;
import dev.eernandorezende.credit_card_api.infra.repositories.FlagRepository;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class FlagService {
    private final FlagRepository flagRepository;

    public List<FlagResponse> getAll(){
        return flagRepository.findAll().stream().map(this::toResponse).toList();
    }

    public FlagResponse getById(Integer id) {
        return flagRepository.findById(id).map(this::toResponse).orElseThrow(FlagNotFoundException::new);
    }

    public FlagResponse create(FlagRequest request) {
        Flag flag = toEntity(request);
        flag = flagRepository.save(flag);
        return toResponse(flag);
    }

    public void update(FlagRequest request, Integer id) {
        Flag flag = flagRepository.findById(id).orElseThrow(FlagNotFoundException::new);
        flag.setName(request.name());
        flagRepository.save(flag);
    }

    public void delete(Integer id) {
        flagRepository.deleteById(id);
    }

    private FlagResponse toResponse(Flag flag) {
        return new FlagResponse(flag.getId(), flag.getName());
    }

    private Flag toEntity(FlagRequest request) {
        return Flag.builder().name(request.name()).build();
    }
}

