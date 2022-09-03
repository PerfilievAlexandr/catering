package com.catering.app.model.api.response;

import com.catering.app.model.enums.user.ESecurityUserRoles;
import com.catering.app.model.enums.user.EUserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserResponse {
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private List<ESecurityUserRoles> roleEntities;
    private EUserStatus status;
}
