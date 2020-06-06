package com.gta.remuniration.exception;

import static com.gta.remuniration.exception.FunctionalErrorCode.USER_DEACTIVATED;

public class UserDeactivatedException extends FunctionalException {

    private static final long serialVersionUID = -3372433568932641320L;

    public UserDeactivatedException() {
        super(USER_DEACTIVATED);
    }

}
