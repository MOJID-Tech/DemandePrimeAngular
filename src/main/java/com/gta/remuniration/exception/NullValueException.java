package com.gta.remuniration.exception;
import java.util.List;

import static com.gta.remuniration.exception.FunctionalErrorCode.NOT_NULL_FIELD;
import static com.gta.remuniration.exception.FunctionalErrorCode.NOT_NULL_FIELDS;
import static java.lang.String.join;

public class NullValueException extends  FunctionalException {


    public NullValueException(final String field) {
        super(NOT_NULL_FIELD, field);
    }

    public NullValueException(final List<String> fields) {
        super(NOT_NULL_FIELDS, join(", ", fields));
    }
}
