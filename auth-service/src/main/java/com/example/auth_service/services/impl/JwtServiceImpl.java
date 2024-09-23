package com.example.auth_service.services.impl;

import com.example.auth_service.commons.dto.TokenResponse;
import com.example.auth_service.services.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {

    private final String secretToken;

    public JwtServiceImpl(@Value("${jwt.secret}") String secretToken) {
        this.secretToken = secretToken;
    }

    @Override
    public TokenResponse generateToken(Long userId) {
        Date expirationDate = new Date(Long.MAX_VALUE);
        String token = Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.ES512, this.secretToken)
                .compact();

        return TokenResponse
                .builder()
                .acessToken(token)
                .build();
    }

    @Override
    public Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(this.secretToken)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public boolean isTokenExpired(String token) {
        try {
            return getClaimsFromToken(token)
                    .getExpiration()
                    .before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Integer getUserIdFromToken(String token) {
        try {
            return Integer.parseInt(getClaimsFromToken(token)
                    .getSubject());
        } catch (Exception e) {
            return null;
        }
    }
}
