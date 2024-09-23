package com.example.gameserviceapi.controller.impl;

import com.example.gameserviceapi.commons.dto.ErrorResponse;
import com.example.gameserviceapi.commons.exceptions.GameException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerController {
    @ExceptionHandler(Exception.class)
    ResponseEntity<ErrorResponse> handleError(GameException gameException) {
        log.error(gameException.getMessage(), gameException);
        var errorResponse = ErrorResponse.builder()
                .status(gameException.getHttpStatus().value())
                .message(gameException.getMessage())
                .build();
        return ResponseEntity.status(gameException.getHttpStatus()).body(errorResponse);
    }
}
