package com.example.app.security.controller;

import com.example.app.exception.RefreshTokenException;
import com.example.app.security.model.api.request.RefreshTokenRequest;
import com.example.app.security.model.api.request.SigninRequest;
import com.example.app.security.model.api.request.SignupRequest;
import com.example.app.security.model.api.response.JwtResponse;
import com.example.app.security.model.api.response.RefreshTokenResponse;
import com.example.app.security.model.dto.SigninDto;
import com.example.app.security.model.entity.User;
import com.example.app.security.repository.UserRepository;
import com.example.app.security.service.RefreshTokenService;
import com.example.app.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RefreshTokenService refreshTokenService;
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    @PreAuthorize("hasAuthority(T(com.example.app.security.model.enums.ESecurityUserPermissions).ADMIN_READ)")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @PostMapping("/signin")
    public ResponseEntity<?> loginUser(@Valid @RequestBody SigninRequest signinRequest) {
        SigninDto signinDto = userService.loginUser(signinRequest);

        return ResponseEntity.ok(new JwtResponse(
                signinDto.getToken(),
                signinDto.getRefreshToken(),
                signinDto.getUsername(),
                signinDto.getRoles()
        ));
    }

    @PostMapping("/signup")
    @PreAuthorize("hasAuthority(T(com.example.app.security.model.enums.ESecurityUserPermissions).ADMIN_WRITE)")
    public void registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        userService.registerUser(signupRequest);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody RefreshTokenRequest request) throws RefreshTokenException {
        String refreshedToken = refreshTokenService.getRefreshedToken(request.getRefreshToken());

        return ResponseEntity.ok(new RefreshTokenResponse(refreshedToken, request.getRefreshToken()));
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority(T(com.example.app.security.model.enums.ESecurityUserPermissions).ADMIN_WRITE)")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("userId") Integer userId) {
        userRepository.deleteById(userId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
