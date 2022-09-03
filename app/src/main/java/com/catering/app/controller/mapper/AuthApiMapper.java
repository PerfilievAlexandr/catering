package com.catering.app.controller.mapper;

import com.catering.app.model.api.request.SigninRequest;
import com.catering.app.model.api.request.SignupRequest;
import com.catering.app.model.api.response.UserResponse;
import com.catering.app.model.api.response.AuthResponse;
import com.catering.app.model.domain.User;
import com.catering.app.model.dto.SigninDto;
import com.catering.app.model.dto.SignupDto;

public class AuthApiMapper {
    public static UserResponse mapToUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getRoles(),
                user.getStatus()
        );
    }

    public static SignupDto mapToSignupDto(SignupRequest signupRequestDto) {
        SignupDto signupDto = new SignupDto();
        signupDto.setEmail(signupRequestDto.getEmail());
        signupDto.setFirstName(signupRequestDto.getFirstName());
        signupDto.setLastName(signupRequestDto.getLastName());
        signupDto.setPassword(signupRequestDto.getPassword());
        signupDto.setRoles(signupRequestDto.getRoles());
        signupDto.setStatus(signupRequestDto.getStatus());

        return signupDto;
    }

    public static SigninDto mapToSigninDto(SigninRequest signinRequest) {
        return new SigninDto(
                signinRequest.getUsername(),
                signinRequest.getPassword()
        );
    }

    public static AuthResponse mapToAuthResponse(AuthResponse authResponse) {
        return new AuthResponse(
                authResponse.getToken(),
                authResponse.getRefreshToken(),
                authResponse.getUsername(),
                authResponse.getRoles()
        );
    }
}
