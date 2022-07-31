package com.catering.app.security.service;

import com.catering.app.security.jwt.JwtUtils;
import com.catering.app.security.model.UserDetailsImpl;
import com.catering.app.security.model.entity.RefreshToken;
import com.catering.app.security.model.entity.Role;
import com.catering.app.security.model.enums.ESecurityUserRoles;
import com.catering.app.security.model.enums.EUserStatus;
import com.catering.app.security.repository.RoleRepository;
import com.catering.app.security.model.api.request.SigninRequest;
import com.catering.app.security.model.api.request.SignupRequest;
import com.catering.app.security.model.dto.SigninDto;
import com.catering.app.security.model.entity.User;
import com.catering.app.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
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

    public SigninDto loginUser(SigninRequest signinRequest) {
        Authentication authenticationRequest = new UsernamePasswordAuthenticationToken(
                signinRequest.getUsername(),
                signinRequest.getPassword()
        );

        Authentication authentication = authenticationManager.authenticate(authenticationRequest);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String token = jwtUtils.generateJwtToken(authentication);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
        List<String> roles = userDetails
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .filter(role -> role.startsWith("ROLE_"))
                .toList();

        return new SigninDto(
                token,
                refreshToken.getToken(),
                userDetails.getUsername(),
                roles
        );
    }

    public void registerUser(SignupRequest signupRequest) {
        if (userRepository.existsUserByEmail(signupRequest.getEmail())) {
            throw new RuntimeException("Error: Email is already in use!");
        }

        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setFirstName(signupRequest.getFirstName());
        user.setLastName(signupRequest.getLastName());
        user.setStatus(EUserStatus.ACTIVE);

        List<ESecurityUserRoles> strRoles = signupRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            Role data = roleRepository.findRoleByName(role)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(data);
        });

        user.setRoles(roles);

        userRepository.save(user);
    }
}
