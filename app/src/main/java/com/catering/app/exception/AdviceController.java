package com.catering.app.exception;

import com.catering.app.exception.errors.ApiError;
import com.catering.app.exception.errors.ApiErrorType;
import com.catering.app.exception.message.BaseMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class AdviceController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(AuthenticationException.class)
    protected ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex, WebRequest request) {
        ApiError error = new ApiError();
        error.setCode(HttpStatus.BAD_REQUEST.value());
        error.setMessage(ex.getLocalizedMessage());
        error.setError(ApiErrorType.VALIDATION);

        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

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

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<BaseMessage> baseMessages = ex.getBindingResult().getAllErrors()
                .stream()
                .map(error -> new BaseMessage(ApiErrorType.VALIDATION, error.getDefaultMessage()))
                .collect(Collectors.toList());

        ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(), ex.getLocalizedMessage(), ApiErrorType.VALIDATION, baseMessages);

        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
}
