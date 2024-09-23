package com.example.auth_service.services;

import com.example.auth_service.commons.dto.TokenResponse;
import com.example.auth_service.commons.dto.UserLogin;
import com.example.auth_service.commons.dto.UserRequest;

public interface AuthService {
    TokenResponse createUser(UserRequest userRequest);
    TokenResponse login(UserLogin userLogin);
}
