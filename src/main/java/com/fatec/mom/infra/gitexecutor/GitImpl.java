package com.fatec.mom.infra.gitexecutor;

import com.fatec.mom.domain.document.DocumentDescriptor;
import com.fatec.mom.infra.gitexecutor.commands.GitCommand;
import com.fatec.mom.infra.gitexecutor.commands.GitCommandAdapter;
import com.fatec.mom.infra.gitexecutor.handler.GitHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GitImpl implements Git {

    @Autowired
    private GitCommandAdapter commandAdapter;

    @Override
    public void getActualBranch(final DocumentDescriptor descriptor) throws IOException {
        final String[] command = commandAdapter.adaptActualBranchCommand(descriptor);
        final GitHandler handler = new GitHandler(GitCommand.REV_PARSE, command);
        handler.run();
    }
}
