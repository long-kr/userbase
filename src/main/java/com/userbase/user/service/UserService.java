package com.userbase.user.service;

import java.util.List;

import com.userbase.user.dto.UserDto;

public interface UserService {
    void createUser(UserDto userDto);

    UserDto updateUser(long id, UserDto userDto);

    UserDto findUserById(long id);

    List<UserDto> getAll();

    void deleteUser(long id);
}
