package com.fatec.mom.domain.revision;

import com.fatec.mom.domain.git.Git;
import com.fatec.mom.domain.git.commands.branch.GitBranch;
import com.fatec.mom.domain.git.exceptions.BranchException;
import com.fatec.mom.domain.git.repository.GitRepository;
import com.fatec.mom.domain.git.repository.GitRepositoryManager;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class RevisionService {

    private static final String MASTER = "master";

    private Git gitExecutor;
    private GitRepositoryManager repositoryManager;

    public void createNewRevision(File dir, final String revisionName) throws IOException, GitAPIException {
        final GitRepository repository = repositoryManager.getRepository(dir);
        final GitBranch workingBranch = checkWorkingBranch(repository, revisionName);
    }

    private GitBranch checkWorkingBranch(final GitRepository repository, final String newBranch) throws IOException, GitAPIException {
        final GitBranch actualBranch = gitExecutor.getCurrentBranch(repository);

        if (actualBranch.getName() == null) {
            throw new BranchException("Erro ao encontrar a branch atual");
        }
        return gitExecutor.checkout(repository, actualBranch.getName(), newBranch, true);
    }
}
