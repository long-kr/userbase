package com.userbase.common;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class APIReponse<T> {
    private boolean success;
    private String message;
    private T data;
    private Object errors;
    private Instant timestamp;

    public static <T> APIReponse<T> success(String message, T data) {
        return APIReponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .timestamp(Instant.now())
                .build();
    }

    public static <T> APIReponse<T> failure(String message, Object errors) {
        return APIReponse.<T>builder()
                .success(false)
                .message(message)
                .errors(errors)
                .timestamp(Instant.now())
                .build();
    }
}
