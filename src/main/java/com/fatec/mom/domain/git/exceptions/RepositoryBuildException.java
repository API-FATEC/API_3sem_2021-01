package com.fatec.mom.domain.git.exceptions;

import java.io.IOException;

public class RepositoryBuildException extends IOException {

    public RepositoryBuildException(String message, Throwable cause) {
        super(message, cause);
    }
}
