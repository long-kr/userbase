package com.userbase.service;

import java.util.List;

import com.userbase.dto.CreateRequest;
import com.userbase.dto.UpdateRequest;
import com.userbase.dto.UserDto;

public interface UserService {

    List<UserDto> getAll();

    UserDto findUserById(long id);

    UserDto create(CreateRequest req);

    UserDto update(long id, UpdateRequest req);

}
