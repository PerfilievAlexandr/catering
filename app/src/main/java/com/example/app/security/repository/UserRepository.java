package com.example.app.security.repository;

import com.example.app.security.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByEmail(String email);
    Boolean existsUserByEmail(String userName);
}
