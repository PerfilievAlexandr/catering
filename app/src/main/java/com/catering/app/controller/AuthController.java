package com.catering.app.controller;

import com.catering.app.exception.ServiceException;
import com.catering.app.model.api.response.UserResponse;
import com.catering.app.controller.mapper.AuthApiMapper;
import com.catering.app.model.domain.User;
import com.catering.app.exception.RefreshTokenException;
import com.catering.app.model.api.request.RefreshTokenRequest;
import com.catering.app.model.api.request.SigninRequest;
import com.catering.app.model.api.request.SignupRequest;
import com.catering.app.model.api.response.RefreshTokenResponse;
import com.catering.app.model.api.response.AuthResponse;
import com.catering.app.servise.UserService;
import com.catering.app.servise.impl.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private RefreshTokenService refreshTokenService;
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    @PreAuthorize("hasAuthority(T(com.catering.app.model.enums.user.ESecurityUserPermissions).ADMIN_READ.getPermission())")
    public List<UserResponse> getAllUsers() {
        List<UserResponse> users = userService.getAllUsers()
                .stream()
                .map(AuthApiMapper::mapToUserResponse)
                .toList();

        return users;
    }

    @PostMapping("/signin")
    public AuthResponse loginUser(@Valid @RequestBody SigninRequest signinRequest) throws ServiceException {
        AuthResponse authResponse = userService.loginUser(AuthApiMapper.mapToSigninDto(signinRequest));

        return AuthApiMapper.mapToAuthResponse(authResponse);
    }

    @PostMapping("/signup")
    @PreAuthorize("hasAuthority(T(com.catering.app.model.enums.user.ESecurityUserPermissions).ADMIN_WRITE.getPermission())")
    public UserResponse registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        User user = userService.registerUser(AuthApiMapper.mapToSignupDto(signupRequest));

        return AuthApiMapper.mapToUserResponse(user);
    }

    @PostMapping("/refresh-token")
    public RefreshTokenResponse refreshToken(@Valid @RequestBody RefreshTokenRequest request) throws RefreshTokenException {
        String refreshedToken = refreshTokenService.getRefreshedToken(request.getRefreshToken());

        return new RefreshTokenResponse(refreshedToken, request.getRefreshToken());
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority(T(com.catering.app.model.enums.user.ESecurityUserPermissions).ADMIN_WRITE.getPermission())")
    public void deleteUserById(@PathVariable("userId") Integer userId) {
        userService.deleteUserById(userId);
    }
}
