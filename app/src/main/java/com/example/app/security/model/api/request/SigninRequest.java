package com.example.app.security.model.api.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class SigninRequest {
    @NotNull
    private String username;
    @NotNull
    private String password;
}
