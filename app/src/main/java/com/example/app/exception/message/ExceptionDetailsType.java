package com.example.app.exception.message;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ExceptionDetailsType {
    UNKNOWN_ERROR("Unknown"),
    VALIDATION("Validation"),
    INTERNAL("Internal"),
    BAD_REQUEST("BadRequest"),
    NOT_FOUND("NotFound");

    private final String type;

    public String value() {
        return type;
    }
}
