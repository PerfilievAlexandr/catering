package com.catering.app.security.config;

import com.google.common.net.HttpHeaders;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "application.jwt")
@Component
@NoArgsConstructor
@Getter
@Setter
public class JwtConfig {
    private String secret;
    private String tokenPrefix;
    private int jwtExpirationMs;
    private int jwtRefreshExpirationMs;
    private String authorities;

    public String getAuthorizationHeader() {
        return HttpHeaders.AUTHORIZATION;
    }
}
