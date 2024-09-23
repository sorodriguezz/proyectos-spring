package com.example.auth_service.controllers;

import com.example.auth_service.commons.consants.ApiPathConstants;
import com.example.auth_service.commons.dto.TokenResponse;
import com.example.auth_service.commons.dto.UserRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(ApiPathConstants.VERSION_ROUTE + ApiPathConstants.AUTH_ROUTE)
public interface AuthApi {
    @PostMapping(ApiPathConstants.REGISTER_ROUTE)
    ResponseEntity<TokenResponse> createUser(@RequestBody @Valid UserRequest userRequest);

    @GetMapping()
    ResponseEntity<String>getUser(@RequestAttribute(name = "X-User-Id") String userId );
}
