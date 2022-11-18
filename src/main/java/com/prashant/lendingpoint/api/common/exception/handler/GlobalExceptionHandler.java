package com.prashant.lendingpoint.api.common.exception.handler;

import com.prashant.lendingpoint.api.common.http.ApiError;
import com.prashant.lendingpoint.api.common.http.ApiSubError;
import com.prashant.lendingpoint.api.common.http.ApiValidationError;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Set;
import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle MethodArgumentNotValidException. Triggered when an object fails @Valid
     * validation annotation on Controllers.
     *
     * @param ex      the MethodArgumentNotValidException that is thrown when @Valid
     *                validation fails
     * @param headers the HttpHeaders
     * @param status  the HttpStatus
     * @param request the WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
                                                                  final HttpHeaders headers,
                                                                  final HttpStatus status,
                                                                  final WebRequest request) {
        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage("Validation error");
        apiError.addValidationErrors(ex.getBindingResult().getFieldErrors());
        apiError.addValidationError(ex.getBindingResult().getGlobalErrors());
        return buildResponseEntity(apiError);
    }

    /**
     * HttpMessageNotReadableException is thrown by spring if the incoming json
     * fails parsing.
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex,
                                                                  final HttpHeaders headers,
                                                                  final HttpStatus status,
                                                                  final WebRequest request) {
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, "Malformed JSON request", ex));
    }

    @ExceptionHandler(ValidationException.class)
    protected ResponseEntity<Object> handleValidationFailedException(final RuntimeException ex) {
        return buildResponseEntity(new ApiError(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage(), ex));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(final RuntimeException ex) {
        return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, "No record found", ex));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolationException(final RuntimeException ex) {
        return buildResponseEntity(
            new ApiError(HttpStatus.UNPROCESSABLE_ENTITY, "Details already present in system.", ex));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolationException(final ConstraintViolationException constraintViolationException) {

        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);

        apiError.setMessage("Constraint Validation error");
        final Set<ConstraintViolation<?>> constraintViolations = constraintViolationException.getConstraintViolations();
        final ArrayList<ApiSubError> al = new ArrayList<>();

        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            final ApiValidationError apiValidationError = new ApiValidationError();
            try {
                apiValidationError
                    .setField(((PathImpl) constraintViolation.getPropertyPath()).getLeafNode().toString());
                apiValidationError.setObject(((PathImpl) constraintViolation.getPropertyPath()).getLeafNode()
                    .getParent().getParameterTypes()
                    .get(((PathImpl) constraintViolation.getPropertyPath()).getLeafNode().getParameterIndex())
                    .getSimpleName());
            } catch (final Exception ex) {
                this.logger.error("Exception in getting field values", ex);
            }
            apiValidationError.setMessage(constraintViolation.getMessage());
            apiValidationError.setRejectedValue(constraintViolation.getInvalidValue());
            al.add(apiValidationError);
        }
        apiError.setSubErrors(al);
        return buildResponseEntity(apiError);
    }

    protected ResponseEntity<Object> buildResponseEntity(final ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Object> handleRuntimeException(final RuntimeException ex) {
        return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex));
    }
}