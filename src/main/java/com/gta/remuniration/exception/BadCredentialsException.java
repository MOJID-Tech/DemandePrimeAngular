package com.gta.remuniration.exception;


import static com.gta.remuniration.exception.FunctionalErrorCode.BAD_CREDENTIALS;

public class BadCredentialsException extends FunctionalException {

    public BadCredentialsException() {
        super(BAD_CREDENTIALS);
    }

}
