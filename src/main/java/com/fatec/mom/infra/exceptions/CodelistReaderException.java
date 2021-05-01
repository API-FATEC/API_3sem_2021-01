package com.fatec.mom.infra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class CodelistReaderException extends RuntimeException {

    public CodelistReaderException(String message) {
        super(message);
    }

    public CodelistReaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
