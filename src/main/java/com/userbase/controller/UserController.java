package com.userbase.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.userbase.dto.CreateRequest;
import com.userbase.dto.UpdateRequest;
import com.userbase.dto.UserDto;
import com.userbase.service.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/{api-version}")
@AllArgsConstructor
public class UserController {

    private static final String USER = "/users";
    private static final String USER_ID = USER + "/{id}";

    private final UserService userService;

    @GetMapping(USER)
    public List<UserDto> list() {
        return userService.getAll();
    }

    @GetMapping(USER_ID)
    public UserDto get(@PathVariable long id) {
        return userService.findUserById(id);
    }

    @PostMapping(USER)
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@Valid @RequestBody CreateRequest body) {
        return userService.create(body);
    }

    @PutMapping(USER_ID)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserDto update(@PathVariable long id, @Valid @RequestBody UpdateRequest body) {
        return userService.update(id, body);
    }

}
