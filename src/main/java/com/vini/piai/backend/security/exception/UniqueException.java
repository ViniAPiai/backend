package com.vini.piai.backend.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UniqueException extends RuntimeException{

    public UniqueException(String message) {
        super(message);
    }

}
