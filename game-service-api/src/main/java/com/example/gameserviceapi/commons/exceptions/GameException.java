package com.example.gameserviceapi.commons.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class GameException extends RuntimeException {

    private final HttpStatus httpStatus;

    public GameException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
