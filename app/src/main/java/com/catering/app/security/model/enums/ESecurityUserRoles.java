package com.catering.app.security.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public enum ESecurityUserRoles {
    ADMIN(new HashSet<>(Arrays.asList(ESecurityUserPermissions.ADMIN_WRITE, ESecurityUserPermissions.ADMIN_READ))),
    DIRECTOR(new HashSet<>(Arrays.asList(ESecurityUserPermissions.DIRECTOR_WRITE, ESecurityUserPermissions.DIRECTOR_READ))),
    MANAGER(new HashSet<>(Arrays.asList(ESecurityUserPermissions.MANAGER_WRITE, ESecurityUserPermissions.MANAGER_READ))),
    SALES(new HashSet<>(Arrays.asList(ESecurityUserPermissions.SALES_WRITE, ESecurityUserPermissions.SALES_READ))),
    CONDUCTING(new HashSet<>(Arrays.asList(ESecurityUserPermissions.CONDUCTING_WRITE, ESecurityUserPermissions.CONDUCTING_READ))),
    PRODUCTION(new HashSet<>(Arrays.asList(ESecurityUserPermissions.PRODUCTION_WRITE, ESecurityUserPermissions.PRODUCTION_READ))),
    STORAGE(new HashSet<>(Arrays.asList(ESecurityUserPermissions.STORAGE_WRITE, ESecurityUserPermissions.STORAGE_READ))),
    MARKETING(new HashSet<>(Arrays.asList(ESecurityUserPermissions.MARKETING_WRITE, ESecurityUserPermissions.MARKETING_READ)));

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
