package com.bugblogs.blog.service;

import com.bugblogs.blog.dto.UserDto;

import java.util.List;

public interface UserService {
    void createUser(UserDto userDto);

    UserDto updateUser(long id, UserDto userDto);

    UserDto findUserById(long id);

    List<UserDto> getAll();

    void deleteUser(long id);
}
