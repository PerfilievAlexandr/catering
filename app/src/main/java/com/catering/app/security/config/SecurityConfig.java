package com.catering.app.security.config;

import com.catering.app.security.jwt.AuthEntryPointJwt;
import com.catering.app.security.jwt.JwtConfig;
import com.catering.app.security.jwt.JwtTokenVerifier;
import com.catering.app.security.service.SecurityUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] AUTH_WHITELIST = {
            "/",
            "index,",
            "/css/*",
            "/js/*",
            "/swagger-ui/**",
            "/v3/api-docs",
            "/swagger-resources/**",
            "/api/v1/auth/signin",
            "/api/v1/auth/refresh-token",
            "/api/v1/customers",
    };
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SecurityUserDetailsService applicationSecurityUserDetailsService;
    @Autowired
    private JwtConfig jwtConfig;
    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                    .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(jwtTokenVerifier(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    JwtTokenVerifier jwtTokenVerifier() {
        return new JwtTokenVerifier(jwtConfig);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationSecurityUserDetailsService);

        return provider;
    }
}
