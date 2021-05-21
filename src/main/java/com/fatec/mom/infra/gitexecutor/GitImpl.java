package com.fatec.mom.infra.gitexecutor;

import com.fatec.mom.domain.document.DocumentDescriptor;
import com.fatec.mom.infra.gitexecutor.commands.GitCommand;
import com.fatec.mom.infra.gitexecutor.commands.GitCommandAdapter;
import com.fatec.mom.infra.gitexecutor.commands.GitCommandResult;
import com.fatec.mom.infra.gitexecutor.handler.GitHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class GitImpl extends GitImplBase {

    @Autowired
    private GitCommandAdapter commandAdapter;

    @Override
    public Optional<GitCommandResult> getActualBranch(final DocumentDescriptor descriptor) throws IOException {
        final String[] command = commandAdapter.adaptActualBranchCommand(descriptor);
        final GitHandler handler = new GitHandler(GitCommand.REV_PARSE, command);

        return Optional.of(run(handler, getOutputCollector()));
    }

    @Override
    public Optional<GitCommandResult> checkoutNewBranch(DocumentDescriptor descriptor, String newBranch) throws IOException {
        final String[] command = commandAdapter.adaptCheckoutNewBranchCommand(descriptor, newBranch);
        final GitHandler handler = new GitHandler(GitCommand.CHECKOUT, command);

        return Optional.of(run(handler, getOutputCollector()));
    }

    @Override
    public Optional<GitCommandResult> checkout(DocumentDescriptor descriptor, String branch) throws IOException {
        final String[] command = commandAdapter.adaptCheckoutCommand(descriptor, branch);
        final GitHandler handler = new GitHandler(GitCommand.CHECKOUT, command);

        return Optional.of(run(handler, getOutputCollector()));
    }

    @Override
    public Optional<GitCommandResult> init(DocumentDescriptor descriptor) {
        final String[] command = commandAdapter.adaptInitCommand(descriptor);
        final GitHandler handler = new GitHandler(GitCommand.INIT, command);

        return Optional.of(run(handler, getOutputCollector()));
    }

    @Override
    public Optional<GitCommandResult> addAll(DocumentDescriptor descriptor) {
        final String[] command = commandAdapter.adaptAddAllCommand(descriptor);
        final GitHandler handler = new GitHandler(GitCommand.ADD, command);

        return Optional.of(run(handler, getOutputCollector()));
    }

    @Override
    public Optional<GitCommandResult> commit(DocumentDescriptor descriptor, String message) {
        final String[] command = commandAdapter.adaptCommitCommand(descriptor, message);
        final GitHandler handler = new GitHandler(GitCommand.COMMIT, command);

        return Optional.of((run(handler, getOutputCollector())));
    }

    @Override
    public Optional<GitCommandResult> tag(DocumentDescriptor descriptor, String tag, String message) {
        final String[] command = commandAdapter.adaptTagCommand(descriptor, tag, message);
        final GitHandler handler = new GitHandler(GitCommand.TAG, command);

        return Optional.of((run(handler, getOutputCollector())));
    }
}
