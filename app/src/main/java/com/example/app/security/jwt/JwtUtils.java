package com.example.app.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;

@Component
public class JwtUtils {
    @Autowired
    private JwtConfig jwtConfig;
    @Autowired
    private SecretKey secretKey;

    public String generateJwtToken(Authentication authentication) {
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(jwtConfig.getAuthorities(), authentication.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtConfig.getJwtExpirationMs()))
                .signWith(secretKey)
                .compact();
    }

    public String generateTokenFromUserDetails(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim(jwtConfig.getAuthorities(), userDetails.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtConfig.getJwtExpirationMs()))
                .signWith(secretKey)
                .compact();
    }

    public String getUserName(String token) {
        Claims claims = getClaims(token);

        return claims.getSubject();
    }

    public List<Map<String, String>> getAuthorities(String token) {
        Claims claims = getClaims(token);

        return (List<Map<String, String>>) claims.get(jwtConfig.getAuthorities());
    }

    private Claims getClaims(String token) throws JwtException {
        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token);

        return claimsJws.getBody();
    }
}
