package com.bugblogs.blog.service;

import com.bugblogs.blog.dto.UserDto;
import com.bugblogs.blog.entity.BBUser;
import com.bugblogs.blog.error.APIRequestException;
import com.bugblogs.blog.error.BBlogErrorCode;
import com.bugblogs.blog.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> getAll() {
        List<BBUser> users = userRepository.findAll();
        return users.stream().map(user -> mapToUserDto(user, new UserDto())).toList();
    }

    @Override
    public void createUser(UserDto userDto) {
        Optional<BBUser> optionalUser = userRepository.findByEmail(userDto.getEmail());

        if (optionalUser.isPresent()) {
            throw new APIRequestException(BBlogErrorCode.EMAIL_TAKEN);
        }

        BBUser user = mapToUser(userDto, new BBUser());
        userRepository.save(user);
    }

    @Override
    public UserDto findUserById(long id) {
        BBUser user = userRepository.findById(id)
                .orElseThrow(() -> new APIRequestException(BBlogErrorCode.USER_NOT_FOUND));
        return mapToUserDto(user, new UserDto());
    }

    @Override
    public UserDto updateUser(long id, UserDto userDto) {
        BBUser user = userRepository.findById(id)
                .map(oldUser -> mapToUser(userDto, oldUser))
                .orElseThrow(() -> new APIRequestException(BBlogErrorCode.USER_NOT_FOUND));
        userRepository.save(user);
        return userDto;
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    private UserDto mapToUserDto(BBUser user, UserDto userDto) {
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setStatus(user.getStatus());
        userDto.setPic(user.getPic());
        userDto.setRole(user.getRole());
        return userDto;
    }

    private BBUser mapToUser(UserDto userDto, BBUser user) {
        if (Objects.nonNull(userDto.getUsername())) {
            user.setUsername(userDto.getUsername());
        }

        if (Objects.nonNull(userDto.getEmail())) {
            user.setEmail(userDto.getEmail());
        }

        if (Objects.nonNull(userDto.getPassword())) {
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
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
