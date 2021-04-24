package com.fatec.mom.infra.gitexecutor;

import com.fatec.mom.domain.document.DocumentDescriptor;

import java.io.IOException;

public interface Git {

    void getActualBranch(final DocumentDescriptor descriptor) throws IOException;
}
