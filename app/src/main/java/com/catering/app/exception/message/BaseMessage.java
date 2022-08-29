package com.catering.app.exception.message;

import com.catering.app.exception.errors.ApiErrorType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
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
