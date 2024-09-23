package com.example.auth_service.services.impl;

import com.example.auth_service.commons.dto.TokenResponse;
import com.example.auth_service.commons.dto.UserLogin;
import com.example.auth_service.commons.dto.UserRequest;
import com.example.auth_service.commons.entities.UserModel;
import com.example.auth_service.repositories.UserRepository;
import com.example.auth_service.services.AuthService;
import com.example.auth_service.services.JwtService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public TokenResponse createUser(UserRequest userRequest) {

        Optional<UserModel> user = this.userRepository.findByEmail(userRequest.getEmail());

        if (user.isPresent()) {
            throw new RuntimeException("User exist");
        }

        return Optional.of(userRequest)
                .map(this::mapToEntity)
                .map(userRepository::save)
                .map(userCreated -> jwtService.generateToken(userCreated.getId()))
                .orElseThrow(() -> new RuntimeException("User creation failed"));
    }

    @Override
    public TokenResponse login(UserLogin userLogin) {
        return userRepository.findByEmail(userLogin.getEmail())
                .filter(user -> passwordEncoder.matches(userLogin.getPassword(), user.getPassword()))
                .map(user -> jwtService.generateToken(user.getId()))
                .orElseThrow(() -> new BadCredentialsException("Invalid username or password"));
    }

    private UserModel mapToEntity(UserRequest userRequest) {
        return UserModel
                .builder()
                .email(userRequest.getEmail())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .name(userRequest.getName())
                .role("USER")
                .build();
    }
}
