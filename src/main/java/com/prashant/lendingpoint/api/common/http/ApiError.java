package com.prashant.lendingpoint.api.common.http;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ApiError {

    private int httpStatusCode;

    private HttpStatus status;

    private Date timestamp;

    private String messageCode;

    private String message;

    private String additionalDetail;

    private List<ApiSubError> subErrors;

    public ApiError() {
        this.timestamp = new Date();
    }

    public ApiError(final HttpStatus status) {
        this.timestamp = new Date();
        this.status = status;
        this.httpStatusCode = status.value();
    }

    public ApiError(final HttpStatus status, final Throwable ex) {
        this.timestamp = new Date();
        this.status = status;
        this.httpStatusCode = status.value();
        this.message = ex.getLocalizedMessage();
        this.additionalDetail = "Unexpected error occurred.";
    }

    public ApiError(final HttpStatus status, final String message, final Throwable ex) {
        this.timestamp = new Date();
        this.status = status;
        this.httpStatusCode = status.value();
        this.message = ex.getLocalizedMessage();
        this.additionalDetail = message;
    }

    public void addValidationErrors(final List<FieldError> fieldErrors) {
        fieldErrors.forEach(this::addValidationError);
    }

    public void addValidationError(final List<ObjectError> globalErrors) {
        globalErrors.forEach(this::addValidationError);
    }

    private void addValidationError(final ObjectError objectError) {
        String fieldName = null;
        Object rejectedValue = null;
        if (objectError instanceof FieldError) {
            final FieldError fieldError = (FieldError) objectError;
            fieldName = fieldError.getField();
            rejectedValue = fieldError.getRejectedValue();
        }
        this.addValidationError(objectError.getObjectName(), fieldName, objectError.getDefaultMessage(), rejectedValue);
    }

    private void addValidationError(final String object, final String field, final String message, final Object rejectedValue) {
        addSubError(new ApiValidationError(object, field, message, rejectedValue));
    }

    private void addSubError(final ApiSubError subError) {
        if (this.subErrors == null) {
            this.subErrors = new ArrayList<>();
        }
        this.subErrors.add(subError);
    }

}
