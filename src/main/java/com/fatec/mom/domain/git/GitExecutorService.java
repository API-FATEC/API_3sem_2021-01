package com.fatec.mom.domain.git;

import com.fatec.mom.domain.git.commands.branch.GitBranch;
import com.fatec.mom.domain.git.exceptions.BranchException;
import com.fatec.mom.domain.git.exceptions.RepositoryBuildException;
import com.fatec.mom.domain.git.repository.GitRepository;
import com.fatec.mom.domain.git.repository.GitRepositoryManager;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.nio.file.NotDirectoryException;

@Service
public class GitExecutorService {

    private static final String MASTER = "master";

    @Autowired
    private GitImpl gitExecutor;
    @Autowired
    private GitRepositoryManager gitRepositoryManager;

    public GitBranch getCurrentBranch(@NotNull final GitRepository repository) throws IOException {
        return repository.getBranch();
    }

    public GitRepository initRepository(@NotNull final File dir) throws RepositoryBuildException, NotDirectoryException {
        return gitRepositoryManager.getRepository(dir);
    }

    public GitBranch checkoutToNewBranch(@NotNull final GitRepository repository, final String newBranch) throws GitAPIException, IOException {
        final GitBranch actualBranch = getActualBranch(repository);

        return gitExecutor.checkout(repository, actualBranch.getName(), newBranch, true);
    }

    public GitBranch checkoutToMaster(@NotNull final GitRepository repository) throws GitAPIException, IOException {
//        final GitBranch actualBranch = getActualBranch(repository);
//        if (actualBranch.getName().equals(MASTER)) {
//            return actualBranch;
//        }

        return gitExecutor.checkout(repository, "", MASTER, false);
    }

    private GitBranch getActualBranch(@NotNull final GitRepository repository) throws BranchException, IOException {
        final GitBranch branch = gitExecutor.getCurrentBranch(repository);

        if (branch.getName() == null) {
            throw new BranchException("Erro ao encontrar a branch atual");
        }
        return branch;
    }
}
