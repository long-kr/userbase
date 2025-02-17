// package com.bugblogs.blog.controller;

// import java.util.Optional;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RestController;

// import com.bugblogs.blog.dto.UserDto;
// import com.bugblogs.blog.entity.BBUser;
// import com.bugblogs.blog.service.UserService;
// import com.bugblogs.blog.util.ResponseDto;

// import lombok.AllArgsConstructor;

// // @RestController
// // @AllArgsConstructor
// public class _AuthController {
// private final UserService userService;

// @PostMapping("/register")
// ResponseEntity<ResponseDto<Void>> registration(@RequestBody UserDto userDto)
// {
// Optional<BBUser> existingUser = userService(userDto.getEmail());

// if (existingUser.isPresent()) {
// ResponseDto<Void> responseDto = new ResponseDto<>("Email is already taken",
// null);
// return ResponseEntity.badRequest().body(responseDto);
// }

// userService.saveUser(userDto);

// ResponseDto<Void> responseDto = new ResponseDto<>("Register successful",
// null);
// return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
// }

// }
