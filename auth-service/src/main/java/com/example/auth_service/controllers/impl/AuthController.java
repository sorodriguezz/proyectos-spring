package com.example.auth_service.controllers.impl;

import com.example.auth_service.commons.dto.TokenResponse;
import com.example.auth_service.commons.dto.UserLogin;
import com.example.auth_service.commons.dto.UserRequest;
import com.example.auth_service.controllers.AuthApi;
import com.example.auth_service.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AuthApi {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public ResponseEntity<TokenResponse> createUser(UserRequest userRequest) {
        return ResponseEntity.ok(authService.createUser(userRequest));
    }

    @Override
    public ResponseEntity<String> getUser(String userId) {
        return ResponseEntity.ok(userId);
    }

    @Override
    public ResponseEntity<TokenResponse> login(UserLogin userLogin) {
        return ResponseEntity.ok(authService.login(userLogin));
    }
}
