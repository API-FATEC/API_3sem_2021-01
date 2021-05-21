package com.fatec.mom.infra.gitexecutor;

import com.fatec.mom.domain.document.DocumentDescriptor;
import com.fatec.mom.infra.gitexecutor.branch.BranchType;
import com.fatec.mom.infra.gitexecutor.commands.GitCommandResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Locale;
import java.util.Optional;

@Component
public class GitExecutor {

    @Autowired
    private GitImpl git;

    public Optional<String> getActualBranch(@NotNull final DocumentDescriptor descriptor) {
        Optional<GitCommandResult> result = Optional.empty();
        try {
                result = git.getActualBranch(descriptor);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (result.isPresent()) {
            final GitCommandResult value = result.get();
            if (value.success()) {
                return Optional.of(value.getOutput().get(0));
            }
        }
        return Optional.empty();
    }

    public boolean checkoutNewBranch(@NotNull final DocumentDescriptor descriptor,
                         @NotNull final String branch) {
        Optional<GitCommandResult> result = Optional.empty();
        try {
            result = git.checkoutNewBranch(descriptor, branch);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return checkResult(result);
    }

    public boolean checkout(@NotNull final DocumentDescriptor descriptor,
                            @NotNull final String branch) {
        Optional<GitCommandResult> result = Optional.empty();
        try {
            result = git.checkout(descriptor, branch);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return checkResult(result);
    }

    public boolean checkoutToMaster(@NotNull final DocumentDescriptor descriptor) {
        final String actualBranch = getActualBranch(descriptor).orElseThrow();
        if (!actualBranch.equals(BranchType.MASTER.getName())) {
            return checkout(descriptor, BranchType.MASTER.getName());
        }
        return true;
    }

    private boolean checkResult(final Optional<GitCommandResult> result) {
        if (result.isPresent()) {
            final GitCommandResult value = result.get();
            return value.success();
        }
        return false;
    }

    public boolean init(final DocumentDescriptor descriptor) {
        final var result = git.init(descriptor);

        return checkResult(result);
    }

    public boolean addAll(final DocumentDescriptor descriptor) {
        final var result = git.addAll(descriptor);
        return checkResult(result);
    }

    public boolean commit(final DocumentDescriptor descriptor, final String message) {
        final var result = git.commit(descriptor, message);
        return checkResult(result);
    }

    public boolean tag(final DocumentDescriptor descriptor,
                    final String tag,
                    final String message) {
        final var result = git.tag(descriptor, tag, message);
        return checkResult(result);
    }
}
