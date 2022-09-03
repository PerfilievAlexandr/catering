package com.catering.app.model.enums.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ESecurityUserPermissions {
    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write"),
    DIRECTOR_READ("director:read"),
    DIRECTOR_WRITE("director:write"),
    MANAGER_READ("manager:read"),
    MANAGER_WRITE("manager:write"),
    SALES_READ("sales:read"),
    SALES_WRITE("sales:write"),
    CONDUCTING_READ("conducting:read"),
    CONDUCTING_WRITE("conducting:write"),
    PRODUCTION_READ("production:read"),
    PRODUCTION_WRITE("production:write"),
    STORAGE_READ("storage:read"),
    STORAGE_WRITE("storage:write"),
    MARKETING_READ("marketing:read"),
    MARKETING_WRITE("marketing:write");

    @Getter
    private final String permission;
}
