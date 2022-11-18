package com.prashant.lendingpoint.api.common.http;

import lombok.Data;

@Data
public class ApiValidationError implements ApiSubError {

    String object;

    String field;

    Object rejectedValue;

    String message;

    public ApiValidationError() {
    }

    public ApiValidationError(final String object, final String field, final String message, final Object rejectedValue) {
        this.object = object;
        this.field = field;
        this.message = message;
        this.rejectedValue = rejectedValue;
    }

}
