package dev.eernandorezende.credit_card_api.domain.services;

import dev.eernandorezende.credit_card_api.application.requests.UserRequest;
import dev.eernandorezende.credit_card_api.application.responses.UserResponse;
import dev.eernandorezende.credit_card_api.domain.entities.Role;
import dev.eernandorezende.credit_card_api.domain.entities.User;
import dev.eernandorezende.credit_card_api.domain.exceptions.UserNotFoundException;
import dev.eernandorezende.credit_card_api.infra.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse create(UserRequest request) {
        var user = toEntity(request);

        user = userRepository.save(user);
        return toResponse(user);
    }

    public UserResponse  getById(Long id) {
        return userRepository.findById(id).map(this::toResponse).orElseThrow(UserNotFoundException::new);
    }

    public void updateUser(UserRequest request, Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        user.setName(request.name());
        userRepository.save(user);
    }

    private UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getEmail(), getRoles(user.getRoles()));
    }

    private User toEntity(UserRequest request) {
        return new User(request.name(), request.email(), request.password(), new HashSet<>());
    }

    private static Set<String> getRoles(Set<Role> roles) {
        return roles.stream().map(Role::getRole).collect(Collectors.toSet());
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
