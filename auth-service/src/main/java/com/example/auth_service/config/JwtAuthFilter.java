package com.example.auth_service.config;

import com.example.auth_service.commons.entities.UserModel;
import com.example.auth_service.repositories.UserRepository;
import com.example.auth_service.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public JwtAuthFilter(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Optional.ofNullable(request.getHeader("Authorization"))
                .filter(header -> !header.isBlank())
                .map(header -> header.substring(7))
                .map(jwtService::getUserIdFromToken)
                .flatMap(userId -> userRepository.findById(Long.valueOf(userId)))
                .ifPresent(userDetails -> {
                    request.setAttribute("X-User-Id", userDetails.getId());
                    proccessAuthentication(request, userDetails);
                });
        filterChain.doFilter(request, response);
    }

    private void proccessAuthentication(HttpServletRequest request, UserModel userDetails) {
        String jwtToken = request.getHeader("Authorization").substring(7);
        Optional.of(jwtToken)
                .filter(token -> !jwtService.isTokenExpired(token))
                .ifPresent(token -> {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                });
    }
}
