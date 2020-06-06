package com.gta.remuniration.exception;
import com.gta.remuniration.exception.FunctionalException;

import static com.gta.remuniration.exception.FunctionalErrorCode.INSUFFICIENT_RIGHT;

public class InsufficientRightException extends FunctionalException {

    private static final long serialVersionUID = -3372433568932641320L;

    public InsufficientRightException() {
        super(INSUFFICIENT_RIGHT);
    }

}