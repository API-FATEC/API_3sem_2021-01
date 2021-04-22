package com.fatec.mom.domain.git.exceptions;

import org.eclipse.jgit.api.errors.GitAPIException;

public class BranchException extends GitAPIException {

    public BranchException(String message) {
        super(message);
    }
}
