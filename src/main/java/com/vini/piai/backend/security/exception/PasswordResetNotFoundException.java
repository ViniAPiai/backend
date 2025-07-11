package com.vini.piai.backend.security.exception;

import com.vini.piai.backend.api.utils.ExceptionEnum;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PasswordResetNotFoundException extends RuntimeException{

    public PasswordResetNotFoundException(String email) {
        super(ExceptionEnum.PASSWORD_RESET_NOT_FOUND.getTopic() + " para o e-mail " + email);
    }

}
