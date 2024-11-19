package dev.eernandorezende.credit_card_api.application.controllers;

import dev.eernandorezende.credit_card_api.application.requests.UserRequest;
import dev.eernandorezende.credit_card_api.application.responses.UserResponse;
import dev.eernandorezende.credit_card_api.domain.services.UserService;
import dev.eernandorezende.credit_card_api.infra.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService  userService;

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest request) {
        return ResponseEntity.ok(userService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody UserRequest request) {
        userService.updateUser(request, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
