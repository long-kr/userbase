package com.userbase.user.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.userbase.user.dto.UserDto;
import com.userbase.user.entity.UserEntity;
import com.userbase.user.error.APIRequestException;
import com.userbase.user.error.BBlogErrorCode;
import com.userbase.user.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserDto> getAll() {
        List<UserEntity> users = userRepository.findAll();
        return users.stream().map(user -> mapToUserDto(user, new UserDto())).toList();
    }

    @Override
    public void createUser(UserDto userDto) {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(userDto.getEmail());

        if (optionalUser.isPresent()) {
            throw new APIRequestException(BBlogErrorCode.EMAIL_TAKEN);
        }

        UserEntity user = mapToUser(userDto, new UserEntity());
        userRepository.save(user);
    }

    @Override
    public UserDto findUserById(long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new APIRequestException(BBlogErrorCode.USER_NOT_FOUND));
        return mapToUserDto(user, new UserDto());
    }

    @Override
    public UserDto updateUser(long id, UserDto userDto) {
        UserEntity user = userRepository.findById(id)
                .map(oldUser -> mapToUser(userDto, oldUser))
                .orElseThrow(() -> new APIRequestException(BBlogErrorCode.USER_NOT_FOUND));
        userRepository.save(user);
        return userDto;
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    private UserDto mapToUserDto(UserEntity user, UserDto userDto) {
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setStatus(user.getStatus());
        userDto.setPic(user.getPic());
        userDto.setRole(user.getRole());
        return userDto;
    }

    private UserEntity mapToUser(UserDto userDto, UserEntity user) {
        if (Objects.nonNull(userDto.getUsername())) {
            user.setUsername(userDto.getUsername());
        }

        if (Objects.nonNull(userDto.getEmail())) {
            user.setEmail(userDto.getEmail());
        }

        if (Objects.nonNull(userDto.getPassword())) {
            user.setPassword(userDto.getPassword());
        }

        if (Objects.nonNull(userDto.getStatus())) {
            user.setStatus(userDto.getStatus());
        }

        if (Objects.nonNull(userDto.getPic())) {
            user.setPic(userDto.getPic());
        }

        user.setUpdatedOn(new Date());

        return user;
    }
}
