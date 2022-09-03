package com.catering.app.model.domain;

import com.catering.app.model.enums.user.ESecurityUserRoles;
import com.catering.app.model.enums.user.EUserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private List<ESecurityUserRoles> roles;
    private EUserStatus status;
}
