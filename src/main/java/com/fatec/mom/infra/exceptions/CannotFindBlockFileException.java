package com.fatec.mom.infra.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Slf4j
public class CannotFindBlockFileException extends RuntimeException {

    public CannotFindBlockFileException(String message, Throwable cause) {
        super(message, cause);
        log.error(message, cause);
    }
}
