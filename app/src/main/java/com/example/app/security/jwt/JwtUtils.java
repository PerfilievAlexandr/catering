package com.example.app.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class JwtUtils {
    @Autowired
    private JwtConfig jwtConfig;
    @Autowired
    private SecretKey secretKey;

    public String generateJwtToken(Authentication authentication) {
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("authorities", authentication.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getTokenExpirationAfterDays())))
                .signWith(secretKey)
                .compact();
    }

    public String getUserName(String token) {
        Claims claims = getClaims(token);

        return claims.getSubject();
    }

    public List<Map<String, String>> getAuthorities(String token) {
        Claims claims = getClaims(token);

        return (List<Map<String, String>>) claims.get("authorities");
    }

    private Claims getClaims(String token) throws JwtException {
        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token);

        return claimsJws.getBody();
    }
}
