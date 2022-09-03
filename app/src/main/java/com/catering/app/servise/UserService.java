package com.catering.app.servise;

import com.catering.app.model.api.response.AuthResponse;
import com.catering.app.model.dto.SigninDto;
import com.catering.app.model.domain.User;
import com.catering.app.model.dto.SignupDto;

import java.util.List;

public interface UserService {
    void deleteUserById(Integer id);
    List<User> getAllUsers();
    User registerUser(SignupDto signupDto);
    AuthResponse loginUser(SigninDto signinDtoDto);
}
