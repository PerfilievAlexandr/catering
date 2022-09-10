package com.catering.app.servise.impl;

import com.catering.app.exception.ResourceNotFoundException;
import com.catering.app.exception.ServiceException;
import com.catering.app.model.entity.UserEntity;
import com.catering.app.servise.mapper.UserEntityMapper;
import com.catering.app.model.dto.SigninDto;
import com.catering.app.model.domain.User;
import com.catering.app.security.JwtUtils;
import com.catering.app.security.model.UserDetailsImpl;
import com.catering.app.model.entity.RefreshTokenEntity;
import com.catering.app.model.entity.RoleEntity;
import com.catering.app.repository.RoleRepository;
import com.catering.app.model.api.response.AuthResponse;
import com.catering.app.repository.UserRepository;
import com.catering.app.servise.UserService;
import com.catering.app.model.dto.SignupDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private RefreshTokenService refreshTokenService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    public AuthResponse loginUser(SigninDto signinDto) throws ServiceException {
        Authentication authenticationRequest = new UsernamePasswordAuthenticationToken(
                signinDto.getUsername(),
                signinDto.getPassword()
        );


        Authentication authentication = authenticationManager.authenticate(authenticationRequest);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String token = jwtUtils.generateJwtToken(authentication);
        RefreshTokenEntity refreshTokenEntity = refreshTokenService.createRefreshToken(userDetails.getId());
        List<String> roles = userDetails
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .filter(role -> role.startsWith("ROLE_"))
                .toList();

        return new AuthResponse(
                token,
                refreshTokenEntity.getToken(),
                userDetails.getUsername(),
                roles
        );
    }

    public User registerUser(SignupDto signupDto) {
        if (userRepository.existsUserByEmail(signupDto.getEmail())) {
            throw new RuntimeException("Error: Email is already in use!");
        }

        Set<RoleEntity> roleEntities = signupDto.getRoles()
                .stream()
                .map(role -> roleRepository.findRoleByName(role)
                        .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found.")))
                .collect(Collectors.toSet());

        UserEntity userEntity = userRepository.save(UserEntityMapper.mapToUserEntityFromSignupDto(signupDto, roleEntities, passwordEncoder));

        return UserEntityMapper.mapToUser(userEntity);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserEntityMapper::mapToUser)
                .toList();
    }

    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }
}
