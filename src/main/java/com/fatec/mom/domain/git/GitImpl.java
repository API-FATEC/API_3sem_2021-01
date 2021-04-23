package com.fatec.mom.domain.git;

import com.fatec.mom.domain.git.commands.branch.GitBranch;
import com.fatec.mom.domain.git.commands.commit.GitCommitType;
import com.fatec.mom.domain.git.handler.GitHandler;
import com.fatec.mom.domain.git.repository.GitRepository;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.io.IOException;

@Slf4j
@Component
public class GitImpl implements Git {

    private static final String MERGED = "%s MERGED";
    private static final String MASTER = "master";

    @Override
    public void addAll(@NotNull final GitRepository repository) throws GitAPIException {
        final GitHandler handler = new GitHandler(repository.getGitInstance());
        handler.addAll();
    }

    @Override
    public GitBranch checkout(@NotNull final GitRepository repository,
                              @NotNull String reference,
                              @NotNull String newBranch,
                              final boolean withNewBranch) throws GitAPIException {
        final GitHandler handler = new GitHandler(repository.getGitInstance());
        final GitBranch result = withNewBranch ? handler.doCheckoutWithNewBranch(newBranch) : handler.doCheckout(newBranch);
        if (result.getName() != null) {
            log.error(String.format("It was not possible to check out the branch: %s, returning to branch: %s", newBranch, reference));
            return handler.doCheckout(reference);
        }
        return result;
    }

    @Override
    public void commit(@NotNull GitRepository repository, GitCommitType type) throws IOException, GitAPIException {
        final GitHandler handler = new GitHandler(repository.getGitInstance());
        final String actualBranch = repository.getBranch().getName();
        handler.commit(actualBranch, type);
    }

    @Override
    public void mergeIntoMaster(@NotNull GitRepository repository, GitBranch branchToMerge) throws IOException, GitAPIException {
        final GitHandler handler = new GitHandler(repository.getGitInstance());
        final GitBranch currentBranch = repository.getBranch();
        if (currentBranch.getName().equalsIgnoreCase(MASTER)) {

        }
        handler.mergeLastCommit(repository.resolveChanges(), String.format(MERGED, branchToMerge.getName()));
    }

    @Override
    public GitBranch getCurrentBranch(@NotNull GitRepository repository) throws IOException {
        return repository.getBranch();
    }
}
