package dev.eernandorezende.credit_card_api.domain.services;

import dev.eernandorezende.credit_card_api.application.requests.UserRequest;
import dev.eernandorezende.credit_card_api.application.responses.UserResponse;
import dev.eernandorezende.credit_card_api.domain.entities.Role;
import dev.eernandorezende.credit_card_api.domain.entities.User;
import dev.eernandorezende.credit_card_api.infra.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private UserRepository userRepository;

    public UserResponse create(UserRequest request) {
        var user = toEntity(request);

        user = userRepository.save(user);
        return toResponse(user);
    }

    private static UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getEmail(), getRoles(user.getRoles()));
    }

    private static User toEntity(UserRequest request) {
        return User.builder()
                .name(request.name())
                .email(request.email())
                .password(request.password())
                .build();
    }

    private static Set<String> getRoles(Set<Role> roles) {
        return roles.stream().map(Role::getRole).collect(Collectors.toSet());
    }
}
