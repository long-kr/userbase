package com.userbase.user.error;

import java.time.ZonedDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ValidationExceptionResponseEntity {
    private final Map<String, String> errors;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;
}
