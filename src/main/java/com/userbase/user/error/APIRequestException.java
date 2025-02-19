package com.userbase.user.error;

public class APIRequestException extends RuntimeException {

    public APIRequestException(BBlogErrorCode errorCode) {
        super(errorCode.name());
    }

    public APIRequestException(BBlogErrorCode errorCode, Throwable cause) {
        super(errorCode.name(), cause);
    }

}
