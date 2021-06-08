package com.fatec.mom.infra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmptyFileListException extends RuntimeException {

    public EmptyFileListException(String message) {
        super(message);
    }
}
