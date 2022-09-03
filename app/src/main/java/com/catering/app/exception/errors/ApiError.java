package com.catering.app.exception.errors;

import com.catering.app.exception.message.BaseMessage;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {
    private int code;
    private String message;
    private ApiErrorType error;
    private List<BaseMessage> detailedMessages;
}
