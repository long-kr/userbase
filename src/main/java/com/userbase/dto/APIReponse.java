package com.userbase.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class APIReponse<T> {
    private boolean success;
    private String message;
    private T data;
    private Object errors;
    private Instant timestamp;

    public static <T> APIReponse<T> success(String message, T data) {
        return new APIReponse<>(true, message, data, null, Instant.now());

    }

    public static <T> APIReponse<T> failure(String message, Object errors) {
        return new APIReponse<>(false, message, null, errors, Instant.now());
    }
}
