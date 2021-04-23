package com.fatec.mom.domain.git.handler;

import com.fatec.mom.domain.git.commands.branch.GitBranch;
import com.fatec.mom.domain.git.commands.commit.GitCommitType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.MergeCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NotMergedException;
import org.eclipse.jgit.lib.ObjectId;


@AllArgsConstructor
public class GitHandler {

    @Getter
    private final Git gitInstance;

    public GitBranch doCheckout(final String newBranch) throws GitAPIException {
        final var checkout = gitInstance.checkout().setName(newBranch).call();
        return new GitBranch(checkout.getName());
    }

    public GitBranch doCheckoutWithNewBranch(final String newBranch) throws GitAPIException {
        final var checkout = gitInstance.checkout()
                .setName(newBranch)
                .setCreateBranch(true)
                .call();
        return new GitBranch(checkout.getName());
    }

    public void addAll() throws GitAPIException {
        gitInstance.add()
                .addFilepattern(".")
                .call();
    }

    public void commit(final String branchName, GitCommitType type) throws GitAPIException {
        gitInstance.commit()
                .setMessage(String.format("%s %s", branchName, type.name()))
                .setAll(true)
                .call();
    }

    public void mergeLastCommit(final ObjectId mergeBase, final String message) throws GitAPIException {
        final var result = gitInstance.merge()
                .include(mergeBase)
                .setCommit(true)
                .setMessage(message)
                .setFastForward(MergeCommand.FastForwardMode.NO_FF)
                .call();
        if (!result.getMergeStatus().isSuccessful()) {
            throw new NotMergedException();
        }
    }
}
