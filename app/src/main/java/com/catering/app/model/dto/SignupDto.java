package com.catering.app.model.dto;

import com.catering.app.model.enums.user.ESecurityUserRoles;
import com.catering.app.model.enums.user.EUserStatus;
import lombok.Data;

import java.util.List;

@Data
public class SignupDto {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private List<ESecurityUserRoles> roles;
    private EUserStatus status;
}
