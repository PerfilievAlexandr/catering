package com.catering.app.security.repository;

import com.catering.app.security.model.entity.Role;
import com.catering.app.security.model.enums.ESecurityUserRoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findRoleByName(ESecurityUserRoles name);
}
