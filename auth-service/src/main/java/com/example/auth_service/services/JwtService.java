package com.example.auth_service.services;

import com.example.auth_service.commons.dto.TokenResponse;
import io.jsonwebtoken.Claims;

public interface JwtService {
    TokenResponse generateToken(Long userId);
    Claims getClaimsFromToken(String token);
    boolean isTokenExpired(String token);
    Integer getUserIdFromToken(String token);
}
