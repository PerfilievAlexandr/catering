package com.example.app.security.controller;

import com.example.app.security.jwt.JwtUtils;
import com.example.app.security.model.api.request.SigninRequest;
import com.example.app.security.model.api.request.SignupRequest;
import com.example.app.security.model.api.response.MessageResponse;
import com.example.app.security.model.entity.Role;
import com.example.app.security.model.entity.User;
import com.example.app.security.model.enums.ESecurityUserRoles;
import com.example.app.security.model.enums.EUserStatus;
import com.example.app.security.repository.RoleRepository;
import com.example.app.security.repository.UserRepository;
import com.example.app.security.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('admin:write')")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @PostMapping("/signin")
    public ResponseEntity<?> loginUser(@Valid @RequestBody SigninRequest signinRequest) {
        Authentication authenticationRequest = new UsernamePasswordAuthenticationToken(
                signinRequest.getUsername(),
                signinRequest.getPassword()
        );

        Authentication authentication = authenticationManager.authenticate(authenticationRequest);

        String token = jwtUtils.generateJwtToken(authentication);

        return ResponseEntity.ok(token);
    }


    @PostMapping("/signup")
    @PreAuthorize("hasAuthority('admin:write')")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        if (userRepository.existsUserByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
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

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('admin:write')")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("userId") Integer userId) {
        userRepository.deleteById(userId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
