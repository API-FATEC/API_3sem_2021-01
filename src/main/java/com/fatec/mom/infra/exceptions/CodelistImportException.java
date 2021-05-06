package com.fatec.mom.infra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class CodelistImportException extends RuntimeException {

    public CodelistImportException(String message) {
        super(message);
    }

    public CodelistImportException(String message, Throwable cause) {
        super(message, cause);
    }
}
