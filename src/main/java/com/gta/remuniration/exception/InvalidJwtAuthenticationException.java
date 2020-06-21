package com.gta.remuniration.exception;


import static com.gta.remuniration.exception.FunctionalErrorCode.INVALID_JWT_TOKEN;

public class InvalidJwtAuthenticationException extends FunctionalException {

    public InvalidJwtAuthenticationException() {
        super(INVALID_JWT_TOKEN);
    }

}
