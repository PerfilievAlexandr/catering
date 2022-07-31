package com.catering.app.exception.errors;

import com.catering.app.exception.message.BaseMessage;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ApiError {
    private int code;
    private String message;
    private ApiErrorType error;
    private List<BaseMessage> detailedMessages;
}
