package com.catering.app.repository;

import com.catering.app.model.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Integer> {
    @Override
    Optional<RefreshTokenEntity> findById(Integer integer);
    Optional<RefreshTokenEntity> findByToken(String token);
}
