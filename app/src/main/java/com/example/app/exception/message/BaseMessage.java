package com.example.app.exception.message;

import com.example.app.exception.errors.ApiErrorType;
import lombok.Data;

@Data
public class BaseMessage {
    private ApiErrorType error;
    private String message;
    private String developerMessage;

    public BaseMessage(ApiErrorType error, String message, String developerMessage) {
        this.error = error;
        this.message = message;
        this.developerMessage = developerMessage;
    }

    public BaseMessage(ApiErrorType error, String message) {
        this.error = error;
        this.message = message;
    }
}
