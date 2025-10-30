package com.userbase.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.userbase.dto.CreateRequest;
import com.userbase.dto.UpdateRequest;
import com.userbase.dto.UserDto;
import com.userbase.entity.User;
import com.userbase.exception.UserNotFoundException;
import com.userbase.mapper.UserMapper;
import com.userbase.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// TODO: add exception for db errors

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        log.info("Retrieved {} users from database", users.size());
        return users.stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public UserDto findUserById(long id) {
        log.debug("Attempting to find user with id: {}", id);
        return userRepository.findById(id)
                .map(user -> {
                    log.info("Found user: id={}, email={}", id, user.getEmail());
                    return userMapper.toDto(user);
                })
                .orElseThrow(() -> {
                    log.warn("User not found with id: {}", id);
                    return new UserNotFoundException(id);
                });
    }

    /**
     * Creates a new user in the local database.
     */
    @Override
    public UserDto create(CreateRequest req) {
        User user = userMapper.toEntity(req);
        User savedUser = userRepository.save(user);
        log.info("Successfully created user: id={}, email={}", savedUser.getId(), savedUser.getEmail());
        return userMapper.toDto(savedUser);
    }

    @Override
    public UserDto update(long id, UpdateRequest req) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Cannot update - user not found with id: {}", id);
                    return new UserNotFoundException(id);
                });

        userMapper.updateEntity(user, req);
        log.info(" user: user={}", user);
        User savedUser = userRepository.save(user);
        log.info("Successfully updated user: id={}, email={}", id, savedUser.getEmail());
        return userMapper.toDto(savedUser);
    }

}
