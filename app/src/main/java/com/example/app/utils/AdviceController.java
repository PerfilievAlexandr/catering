package com.example.app.utils;

import com.example.app.exception.ServiceException;
import com.example.app.exception.errors.ApiError;
import com.example.app.exception.errors.ApiErrorType;
import com.example.app.exception.message.BaseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class AdviceController {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAll(Throwable ex, WebRequest request) {
        if (ex instanceof ServiceException exception) {
            ApiError apiError = new ApiError(
                exception.getCode(),
                exception.getMessage(),
                exception.getError(),
                exception.getDetails()
                    .stream()
                    .map((internalDetailSimpleException) -> new BaseMessage(
                        ApiErrorType.valueOf(exception.getError().name()),
                        internalDetailSimpleException.getMessage(),
                        internalDetailSimpleException.getDeveloperMessage()))
                    .collect(Collectors.toList())
            );

            return new ResponseEntity<>(apiError, HttpStatus.valueOf(exception.getCode()));
        } else if (ex instanceof AccessDeniedException) {
            ApiError apiError = new ApiError(
                    HttpStatus.FORBIDDEN.value(),
                    ex.getLocalizedMessage(),
                    ApiErrorType.PERMISSION_DENIED,
                    List.of(new BaseMessage(ApiErrorType.INTERNAL, ex.getCause().getMessage()))
            );

            return new ResponseEntity(apiError, HttpStatus.valueOf(apiError.getCode()));
        }

        ApiError apiError = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getLocalizedMessage(),
                ApiErrorType.VALIDATION,
                List.of(new BaseMessage(ApiErrorType.INTERNAL, ex.getCause().getMessage()))
        );

        return new ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
