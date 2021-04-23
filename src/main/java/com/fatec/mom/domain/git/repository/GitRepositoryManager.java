package com.fatec.mom.domain.git.repository;

import com.fatec.mom.domain.git.exceptions.RepositoryBuildException;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.nio.file.NotDirectoryException;

@Component
public class GitRepositoryManager {

    @Autowired
    private FileRepositoryBuilder builder;

    public GitRepository getRepository(@NotNull File dir) throws RepositoryBuildException, NotDirectoryException {
//        if (!dir.isDirectory()) {
//            throw new NotDirectoryException(String.format("The selected dir: %s is not a directory.", dir.getName()));
//        }
        try {
            return new GitRepository(dir.getName(),
                    builder.setGitDir(dir).readEnvironment().findGitDir().build());
        } catch (IOException e) {
            throw new RepositoryBuildException(String.format("Failed to read the repository in %s", dir.getAbsolutePath()), e);
        }
    }
}
