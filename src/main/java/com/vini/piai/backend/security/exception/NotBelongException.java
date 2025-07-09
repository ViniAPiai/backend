package com.vini.piai.backend.security.exception;

public class NotBelongException extends RuntimeException {
    public NotBelongException(String message) {
        super(message);
    }
}
