package com.example.app.security.repository;

import com.example.app.security.model.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
    @Override
    Optional<RefreshToken> findById(Integer integer);
    Optional<RefreshToken> findByToken(String token);
}
