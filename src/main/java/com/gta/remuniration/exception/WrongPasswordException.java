package com.gta.remuniration.exception;

import static com.gta.remuniration.exception.FunctionalErrorCode.WRONG_PASSWORD;

public class WrongPasswordException extends FunctionalException {



    public WrongPasswordException() {
        super(WRONG_PASSWORD);
    }
}
