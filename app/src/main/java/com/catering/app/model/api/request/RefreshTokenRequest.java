package com.catering.app.model.api.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RefreshTokenRequest {
    @NotBlank
    private String refreshToken;
}
