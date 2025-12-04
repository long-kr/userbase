package com.userbase.service;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.userbase.dto.APIReponse;
import com.userbase.dto.ApplicationDto;
import com.userbase.dto.CreateRequest;
import com.userbase.dto.UpdateRequest;
import com.userbase.dto.UserDto;
import com.userbase.entity.User;
import com.userbase.exception.UserNotFoundException;
import com.userbase.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// TODO: add exception for db errors

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final WebClient.Builder webClientBuilder;

    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        log.info("========> Retrieved {} users from database", users.size());

        return users.stream()
                .map(UserDto::toDto)
                .toList();
    }

    public UserDto findUserById(long id) {
        log.debug("========> Attempting to find user with id: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("User not found with id: {}", id);
                    throw new UserNotFoundException(id);
                });

        ParameterizedTypeReference<APIReponse<List<ApplicationDto>>> typeRef = new ParameterizedTypeReference<>() {
        };

        List<ApplicationDto> applications = webClientBuilder.build()
                .get()
                .uri("http://application-service/api/v1/applications",
                        uriBuilder -> uriBuilder.queryParam("supabaseUserId", user.getSupabaseUserId()).build())
                .retrieve()
                .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                        response -> {
                            log.error("Error calling application-service: status={}", response.statusCode());
                            return response.createException();
                        })
                .bodyToMono(typeRef)
                .map(APIReponse::getData)
                .block();

        UserDto userDto = new UserDto(user.getId(),
                user.getSupabaseUserId(),
                user.getEmail(),
                user.getStatus(),
                user.getTimezone(),
                user.getRole(),
                user.getProfile(),
                applications);

        log.info("========> Found user: id={}, email={}", id, userDto.email());

        return userDto;
    }

    /**
     * Creates a new user in the local database.
     */

    public UserDto create(CreateRequest req) {

        User user = CreateRequest.toEntity(req);
        User savedUser = userRepository.save(user);

        log.info("========> Successfully created user: id={}, email={}", savedUser.getId(), savedUser.getEmail());

        return UserDto.toDto(savedUser);
    }

    public UserDto update(long id, UpdateRequest req) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("========> Cannot update - user not found with id: {}", id);
                    return new UserNotFoundException(id);
                });

        user = UpdateRequest.toEntity(req, user);
        log.info("========> User: user={}", user);

        User savedUser = userRepository.save(user);
        log.info("========> Successfully updated user: id={}, email={}", id, savedUser.getEmail());
        return UserDto.toDto(savedUser);
    }

}
