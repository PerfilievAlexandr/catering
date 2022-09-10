package com.catering.app.servise.impl;

import com.catering.app.exception.ServiceException;
import com.catering.app.exception.errors.ApiErrorType;
import com.catering.app.model.entity.RefreshTokenEntity;
import com.catering.app.exception.RefreshTokenException;
import com.catering.app.model.entity.UserEntity;
import com.catering.app.security.config.JwtConfig;
import com.catering.app.security.JwtUtils;
import com.catering.app.security.model.UserDetailsImpl;
import com.catering.app.repository.RefreshTokenRepository;
import com.catering.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {
    @Autowired
    private JwtConfig jwtConfig;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    @Autowired
    private JwtUtils jwtUtils;

    public String getRefreshedToken(String refreshToken) throws RefreshTokenException {
        RefreshTokenEntity refreshTokenEntityFromDb = findByToken(refreshToken);
        verifyExpiration(refreshTokenEntityFromDb);
        UserDetails userDetails = UserDetailsImpl.constructFromUserDbToUserDetails(refreshTokenEntityFromDb.getUserEntity());
        return jwtUtils.generateTokenFromUserDetails(userDetails);
    }

    private void verifyExpiration(RefreshTokenEntity token) throws RefreshTokenException {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new RefreshTokenException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        }
    }

    public RefreshTokenEntity findByToken(String token) throws RefreshTokenException {
        return refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new RefreshTokenException(token, "Refresh token is not in database!"));
    }

    public RefreshTokenEntity createRefreshToken(Integer userId) throws ServiceException {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new ServiceException(String.format("Пользователь с id = %s не найден", userId), 404, ApiErrorType.NOT_FOUND ));
        RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity();

        refreshTokenEntity.setUserEntity(userEntity);
        refreshTokenEntity.setExpiryDate(Instant.now().plusMillis(jwtConfig.getJwtRefreshExpirationMs()));
        refreshTokenEntity.setToken(UUID.randomUUID().toString());
        refreshTokenEntity = refreshTokenRepository.save(refreshTokenEntity);
        return refreshTokenEntity;
    }
}
