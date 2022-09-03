package com.catering.app.repository;

import com.catering.app.model.entity.RoleEntity;
import com.catering.app.model.enums.user.ESecurityUserRoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    Optional<RoleEntity> findRoleByName(ESecurityUserRoles name);
}
