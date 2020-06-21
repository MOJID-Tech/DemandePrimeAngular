package com.gta.remuniration.exception;

import static com.gta.remuniration.exception.FunctionalErrorCode.PROPERTY_ALREADY_USED;

public class PropertyAlreadyUsedException extends FunctionalException {



    public PropertyAlreadyUsedException(final String property) {
        super(PROPERTY_ALREADY_USED, property);
    }

}

