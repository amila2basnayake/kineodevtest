package com.e3.test.exception;

import com.e3.test.service.dto.ApiSubError;
import com.e3.test.service.dto.ApiValidationError;

import java.util.ArrayList;
import java.util.List;

public class APIValidationException extends RuntimeException {

    private static final long serialVersionUID = 6181019818027784575L;

    private final List<ApiSubError> validationErrors = new ArrayList<>();

    public APIValidationException() {
    }

    public APIValidationException(String message) {
        super(message);
    }

    public APIValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public APIValidationException(Throwable cause) {
        super(cause);
    }

    public APIValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public APIValidationException(String object, String field, String rejectedValue, String validationMessage) {
        validationErrors.add(new ApiValidationError(object, field, rejectedValue, validationMessage));
    }

    public void addValidationErrors(List<ApiSubError> errors) {
        validationErrors.addAll(errors);
    }

    public List<ApiSubError> getValidationErrors() {
        return validationErrors;
    }
}
