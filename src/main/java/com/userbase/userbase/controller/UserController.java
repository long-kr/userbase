package com.bugblogs.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bugblogs.blog.dto.UserDto;
import com.bugblogs.blog.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserController {

    private static final String USER = "/users";
    private static final String USER_ID = USER + "/{id}";

    private final UserService userService;

    @GetMapping(USER)
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    @PostMapping(USER)
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserDto userDto) {
        userService.createUser(userDto);
    }

    @GetMapping(USER_ID)
    public UserDto getUser(@PathVariable long id) {
        return userService.findUserById(id);
    }

    @PutMapping(USER_ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public UserDto updateUser(@PathVariable long id, @RequestBody UserDto userDto) {
        return userService.updateUser(id, userDto);
    }

    @DeleteMapping(USER_ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }
}
