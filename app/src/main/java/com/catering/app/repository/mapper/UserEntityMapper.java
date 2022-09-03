package com.catering.app.repository.mapper;

import com.catering.app.model.domain.User;
import com.catering.app.model.entity.RoleEntity;
import com.catering.app.model.entity.UserEntity;
import com.catering.app.model.dto.SignupDto;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

public class UserEntityMapper {
    public static User mapToUser(UserEntity user) {
        return new User(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                user.getRoleEntities().stream().map(RoleEntity::getName).toList(),
                user.getStatus()
        );
    }

    public static UserEntity mapToUserEntityFromSignupDto(SignupDto signupDto, Set<RoleEntity> roleEntities, PasswordEncoder passwordEncoder) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(signupDto.getEmail());
        userEntity.setFirstName(signupDto.getFirstName());
        userEntity.setLastName(signupDto.getLastName());
        userEntity.setPassword(passwordEncoder.encode(signupDto.getPassword()));
        userEntity.setRoleEntities(roleEntities);
        userEntity.setStatus(signupDto.getStatus());

        return userEntity;
    }
}
