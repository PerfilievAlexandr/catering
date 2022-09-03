package com.catering.app.repository;

import com.catering.app.model.domain.User;
import com.catering.app.model.entity.UserEntity;
import com.catering.app.repository.mapper.UserEntityMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findUserByEmail(String email);
    Boolean existsUserByEmail(String userName);

    default List<User> getAllUsers() {
        return findAll()
                .stream()
                .map(UserEntityMapper::mapToUser)
                .toList();
    }

    default User registerUser(UserEntity userEntity) {
        return UserEntityMapper.mapToUser(save(userEntity));
    }
}
