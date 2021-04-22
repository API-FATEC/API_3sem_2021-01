package com.fatec.mom.domain.git.repository;

import com.fatec.mom.domain.git.commands.branch.GitBranch;
import lombok.AllArgsConstructor;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;

import java.io.File;
import java.io.IOException;

@AllArgsConstructor
public class GitRepository {

    private final Repository repository;

    public Git getGitInstance() {
        return new Git(repository);
    }

    public File getLocalPath() {
        return repository.getWorkTree();
    }

    public GitBranch getBranch() throws IOException {
        return new GitBranch(repository.getBranch());
    }

    public ObjectId resolveChanges() throws IOException {
        return repository.resolve("changes");
    }
}
