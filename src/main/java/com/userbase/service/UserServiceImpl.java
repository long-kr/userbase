package com.userbase.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.userbase.common.Status;
import com.userbase.common.UserRole;
import com.userbase.dto.CreateRequest;
import com.userbase.dto.UpdateRequest;
import com.userbase.dto.UserDto;
import com.userbase.entity.User;
import com.userbase.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());
    private final UserRepository userRepository;

    @Override
    public List<UserDto> getAll() {
        logger.info("Fetching all users from the database");
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserServiceImpl::toDto)
                .toList();
    }

    @Override
    public UserDto findUserById(long id) {
        return userRepository.findById(id)
                .map(UserServiceImpl::toDto)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    /**
     * TODO: Syncs a new user from Supabase auth to the local database.
     */
    @Override
    public UserDto create(CreateRequest req) {
        User user = toCreate(req);
        return toDto(userRepository.save(user));
    }

    @Override
    public UserDto update(long id, UpdateRequest req) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user = toUpdate(user, req);
        userRepository.save(user);
        return toDto(user);
    }

    public static UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getEmail(), user.getStatus(),
                user.getTimezone(), user.getProfile());
    }

    public static User toCreate(CreateRequest target) {
        User user = new User();

        user.setSupabaseUserId(target.supbaseUserId());
        user.setEmail(target.email());
        user.setProfile(target.profile());
        user.setRole(UserRole.USER);
        user.setUpdatedOn(LocalDateTime.now());
        user.setStatus(Status.PENDING);

        if (target.timezone() != null) {
            user.setTimezone(target.timezone());
        }

        return user;
    }

    public static User toUpdate(User user, UpdateRequest target) {
        if (target.profile() != null) {
            user.setProfile(target.profile());
        }

        if (target.timezone() != null) {
            user.setTimezone(target.timezone());
        }

        user.setUpdatedOn(LocalDateTime.now());

        return user;

    }

}
