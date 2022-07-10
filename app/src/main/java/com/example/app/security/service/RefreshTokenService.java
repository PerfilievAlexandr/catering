package com.example.app.security.service;

import com.example.app.exception.RefreshTokenException;
import com.example.app.security.jwt.JwtConfig;
import com.example.app.security.jwt.JwtUtils;
import com.example.app.security.model.UserDetailsImpl;
import com.example.app.security.model.entity.RefreshToken;
import com.example.app.security.repository.RefreshTokenRepository;
import com.example.app.security.repository.UserRepository;
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
        RefreshToken refreshTokenFromDb = findByToken(refreshToken);
        verifyExpiration(refreshTokenFromDb);
        UserDetails userDetails = UserDetailsImpl.constructFromUserDbToUserDetails(refreshTokenFromDb.getUser());
        return jwtUtils.generateTokenFromUserDetails(userDetails);
    }

    public RefreshToken findByToken(String token) throws RefreshTokenException {
        return refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new RefreshTokenException(token, "Refresh token is not in database!"));
    }

    public RefreshToken createRefreshToken(Integer userId) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(userRepository.findById(userId).get());
        refreshToken.setExpiryDate(Instant.now().plusMillis(jwtConfig.getJwtRefreshExpirationMs()));
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token) throws RefreshTokenException {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new RefreshTokenException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        }

        return token;
    }
}
