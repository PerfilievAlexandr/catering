package com.catering.app.exception;

import com.catering.app.exception.errors.ApiErrorType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ServiceException extends Exception {
    private Integer code;
    private ApiErrorType error;
    private List<InternalDetailSimpleException> details;

    public ServiceException (String message, Integer code) {
        super(message);
        this.code = code;
        this.error = ApiErrorType.UNKNOWN_ERROR;
        this.details = new ArrayList<>();
    }

    public ServiceException(String message, Integer code, ApiErrorType error) {
        super(message);
        this.code = code;
        this.error = error;
        this.details = new ArrayList<>();
    }

    public ServiceException(String message, Integer code, ApiErrorType error, List<InternalDetailSimpleException> details) {
        super(message);
        this.code = code;
        this.error = error;
        this.details = details;
    }
}
