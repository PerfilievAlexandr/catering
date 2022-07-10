package com.example.app.security.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SigninDto {
    private String token;
    private String refreshToken;
    private String username;
    private List<String> roles;
}
