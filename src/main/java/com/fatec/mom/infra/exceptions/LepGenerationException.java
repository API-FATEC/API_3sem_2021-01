package com.fatec.mom.infra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class LepGenerationException extends RuntimeException {

    public LepGenerationException(String message) {
        super(message);
    }
}
