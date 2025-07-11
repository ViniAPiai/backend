package com.vini.piai.backend.api.utils;

import lombok.Getter;

/**
 * An enum for putting the exception throw in the code, so the frontend can translate then by the language the user selected
 */
@Getter
public enum ExceptionEnum {

    PASSWORD_RESET_NOT_FOUND("passwordResetNotFound"),
    USER_NOT_FOUND("userNotFound"),
    ROLE_NOT_FOUND("roleNotFound"),
    REFRESH_TOKEN_IS_EXPIRED_PLEASE_LOGIN("refreshTokenIsExpiredPleaseLogin"),

    ;


    private final String topic;

    ExceptionEnum(String topic) {
        this.topic = topic;
    }
}
