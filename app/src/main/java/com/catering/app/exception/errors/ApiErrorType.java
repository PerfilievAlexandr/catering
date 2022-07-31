package com.catering.app.exception.errors;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ApiErrorType {
    AUTH_REQUIRED("AuthRequired"),
    AUTH_FAILED("AuthenticationFailed"),
    UNPROCESSABLE_ENTITY("UnprocessableEntity"),
    INVALID_REFRESH_TOKEN("InvalidRefreshToken"),
    INVALID_ACCESS_TOKEN("InvalidAccessToken"),
    PERMISSION_DENIED("PermissionDenied"),
    UNKNOWN_ERROR("Unknown"),
    VALIDATION("Validation"),
    INTERNAL("Internal"),
    BAD_REQUEST("BadRequest"),
    NOT_FOUND("NotFound");

    private final String message;

    public String value() {
        return message;
    }
}
