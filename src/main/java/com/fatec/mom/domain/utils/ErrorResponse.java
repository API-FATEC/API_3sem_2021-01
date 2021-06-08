package com.fatec.mom.domain.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Getter @Setter @RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private final int status;
    private final String message;
    private final String stackTrace;
    private List<ValidationError> errors;

    @Getter @Setter @AllArgsConstructor
    private static class ValidationError {
        private final String field;
        private final String message;
    }

    public void addValidationError(String field, String message) {
        if (errors == null) {
            errors = new LinkedList<>();
        }
        errors.add(new ValidationError(field, message));
    }

    public static ResponseEntity<ErrorResponse> build(final Exception exception,
                                                      final HttpStatus status) {
        return build(exception, exception.getMessage(), status);
    }

    private static ResponseEntity<ErrorResponse> build(final Exception exception,
                                                       final String message,
                                                       final HttpStatus status) {
        final var response = new ErrorResponse(status.value(), message, Arrays.toString(exception.getStackTrace()));
        return ResponseEntity
                .status(status)
                .body(response);
    }
}