package com.gta.remuniration.exception;

import static  com.gta.remuniration.exception.FunctionalErrorCode.CREDENTIAL_ALREADY_USED;

public class CredentialAlreadyExistsException extends FunctionalException {



    public CredentialAlreadyExistsException(final String property) {
        super(CREDENTIAL_ALREADY_USED, property);
    }

}