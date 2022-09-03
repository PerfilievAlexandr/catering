package com.catering.app.model.api.request;

import com.catering.app.model.enums.user.ESecurityUserRoles;
import com.catering.app.model.enums.user.EUserStatus;
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
