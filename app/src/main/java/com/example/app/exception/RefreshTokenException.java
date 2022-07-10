package com.example.app.exception;

import com.example.app.exception.errors.ApiErrorType;
import org.springframework.http.HttpStatus;

public class RefreshTokenException extends ServiceException {
    public RefreshTokenException(String token, String message) {
        super(String.format("Failed for [%s]: %s", token, message), HttpStatus.FORBIDDEN.value(), ApiErrorType.INVALID_REFRESH_TOKEN);
    }
}
