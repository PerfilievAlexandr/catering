package com.example.app.security.repository;

import com.example.app.security.model.entity.Role;
import com.example.app.security.model.enums.ESecurityUserRoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findRoleByName(ESecurityUserRoles name);
}
