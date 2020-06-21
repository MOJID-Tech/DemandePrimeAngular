package com.gta.remuniration.exception;
import com.gta.remuniration.exception.FunctionalException;

import static com.gta.remuniration.exception.FunctionalErrorCode.INSUFFICIENT_RIGHT;

public class InsufficientRightException extends FunctionalException {



    public InsufficientRightException() {
        super(INSUFFICIENT_RIGHT);
    }

}