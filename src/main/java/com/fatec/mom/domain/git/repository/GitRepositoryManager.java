package com.fatec.mom.domain.git.repository;

import com.fatec.mom.domain.git.exceptions.RepositoryBuildException;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;

@Component
public class GitRepositoryManager {

    private FileRepositoryBuilder builder;

    public GitRepository getRepository(@NotNull File dir) throws RepositoryBuildException {
        try {
            return new GitRepository(builder.setGitDir(dir).readEnvironment().findGitDir().build());
        } catch (IOException e) {
            throw new RepositoryBuildException(String.format("Failed to read the repository in %s", dir.getAbsolutePath()), e);
        }
    }
}
