package com.catering.app.exception;

import com.catering.app.exception.message.ExceptionDetailsType;
import lombok.Data;

@Data
public class InternalDetailSimpleException {
    private final ExceptionDetailsType type;
    private final String message;
    private String developerMessage = "";

    public InternalDetailSimpleException(ExceptionDetailsType type, String message) {
        this.type = type;
        this.message = message;
    }

    public InternalDetailSimpleException(ExceptionDetailsType type, String message, String developerMessage) {
        this.type = type;
        this.message = message;
        this.developerMessage = developerMessage;
    }
}
