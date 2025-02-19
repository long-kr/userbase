package com.userbase.user.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto<T> {
    private boolean status;
    private T data;
    private String errorCode;

    public ResponseDto(boolean status) {
        this.status = status;
    }

    public ResponseDto(boolean status, T data) {
        this.status = status;
        this.data = data;
    }

    public static ResponseDto<Void> success() {
        return new ResponseDto<Void>(true);
    }

    public static <T> ResponseDto<T> success(T data) {
        return new ResponseDto<>(true, data);
    }

    public static <T> ResponseDto<T> fail(String errorCode, HttpStatus status) {
        return new ResponseDto<>(false, null, errorCode);
    }

}
