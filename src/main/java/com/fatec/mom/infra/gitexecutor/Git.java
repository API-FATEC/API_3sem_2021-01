package com.fatec.mom.infra.gitexecutor;

import com.fatec.mom.domain.document.DocumentDescriptor;
import com.fatec.mom.infra.gitexecutor.commands.GitCommandResult;

import java.io.IOException;
import java.util.Optional;

public interface Git {

    Optional<GitCommandResult> getActualBranch(final DocumentDescriptor descriptor) throws IOException;
    Optional<GitCommandResult> checkoutNewBranch(final DocumentDescriptor descriptor, final String newBranch) throws IOException;
    Optional<GitCommandResult> checkout(final DocumentDescriptor descriptor, final String newBranch) throws IOException;
    Optional<GitCommandResult> init(final DocumentDescriptor descriptor);
}
