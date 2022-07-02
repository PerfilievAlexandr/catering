package com.example.app.security.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public enum ESecurityUserRoles {
    ADMIN(new HashSet<>(Arrays.asList(ESecurityUserPermissions.ADMIN_WRITE, ESecurityUserPermissions.ADMIN_READ))),
    USER(new HashSet<>(List.of(ESecurityUserPermissions.USER_READ)));

    private final Set<ESecurityUserPermissions> permissions;

    public Set<SimpleGrantedAuthority> getSimpleGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());

        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return permissions;
    }
}
