package com.gta.remuniration.exception;

import static com.gta.remuniration.exception.FunctionalErrorCode.SEND_EMAIL;

public class SendEmailException extends FunctionalException {



    public SendEmailException() {
        super(SEND_EMAIL);
    }

}
