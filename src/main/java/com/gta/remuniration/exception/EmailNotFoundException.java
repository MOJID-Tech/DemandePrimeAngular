package com.gta.remuniration.exception;



import static com.gta.remuniration.exception.FunctionalErrorCode.EMAIL_NOT_FOUND;

public class EmailNotFoundException extends FunctionalException {



    public EmailNotFoundException() {
        super(EMAIL_NOT_FOUND);
    }
}
