package com.catering.app.security.model.api.request;

import com.catering.app.security.model.enums.ESecurityUserRoles;
import com.catering.app.security.model.enums.EUserStatus;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class SignupRequest {
    @NotNull
    private String email;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String password;
    @NotNull
    private List<ESecurityUserRoles> roles;
    private EUserStatus status;
}
