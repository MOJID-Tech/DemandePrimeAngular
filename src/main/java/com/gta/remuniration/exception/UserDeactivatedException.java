package com.gta.remuniration.exception;

import static com.gta.remuniration.exception.FunctionalErrorCode.USER_DEACTIVATED;

public class UserDeactivatedException extends FunctionalException {



    public UserDeactivatedException() {
        super(USER_DEACTIVATED);
    }

}
