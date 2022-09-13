package com.catering.app.exception;

import com.catering.app.exception.errors.ApiErrorType;
import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ServiceException {
    public ResourceNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND.value(), ApiErrorType.NOT_FOUND);
    }
}