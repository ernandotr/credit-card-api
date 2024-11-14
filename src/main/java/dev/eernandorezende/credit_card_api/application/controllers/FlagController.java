package dev.eernandorezende.credit_card_api.application.controllers;

import dev.eernandorezende.credit_card_api.application.requests.FlagRequest;
import dev.eernandorezende.credit_card_api.application.responses.FlagResponse;
import dev.eernandorezende.credit_card_api.domain.entities.Flag;
import dev.eernandorezende.credit_card_api.domain.services.FlagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/flags")
public class FlagController {

    private final FlagService flagService;

    @GetMapping
    public ResponseEntity<List<FlagResponse>> getAll() {
        return ResponseEntity.ok(flagService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlagResponse> getById(@PathVariable  Integer id) {
        return ResponseEntity.ok(flagService.getById(id));
    }

    @PostMapping
    public ResponseEntity<FlagResponse> create(@RequestBody FlagRequest request) {
        return ResponseEntity.ok(flagService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody FlagRequest request, @PathVariable Integer id) {
        flagService.update(request, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        flagService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
