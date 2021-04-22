package com.fatec.mom.domain.git;

import com.fatec.mom.domain.git.commands.branch.GitBranch;
import com.fatec.mom.domain.git.commands.commit.GitCommitType;
import com.fatec.mom.domain.git.repository.GitRepository;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.io.IOException;

@Component
public interface Git {

    void addAll(@NotNull final GitRepository repository) throws GitAPIException;

    void commit(@NotNull final GitRepository repository, final GitCommitType type) throws IOException, GitAPIException;

    void mergeIntoMaster(@NotNull final GitRepository repository,
                         final GitBranch branchToMerge) throws IOException, GitAPIException;

    GitBranch checkout(@NotNull final GitRepository repository,
                       @NotNull final String reference,
                       @NotNull final String newBranch,
                       final boolean withNewBranch) throws GitAPIException;

    GitBranch getCurrentBranch(@NotNull final GitRepository repository) throws IOException;
}
