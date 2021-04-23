package com.fatec.mom.test.domain.git;

import com.fatec.mom.domain.git.GitExecutorService;
import com.fatec.mom.domain.git.commands.branch.GitBranch;
import com.fatec.mom.domain.git.exceptions.RepositoryBuildException;
import com.fatec.mom.domain.git.repository.GitRepository;
import com.fatec.mom.test.integration.IntegrationTest;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.File;
import java.io.IOException;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;
import java.nio.file.Paths;

@IntegrationTest
public class GitCommandsTest {

    @Autowired
    private GitExecutorService gitExecutorService;

    @Test
    @DisplayName("Open a Repository")
    void openARepository() throws IOException, GitAPIException {
        final File dir = new File("D:/Documents/facul/PI III/git-repository");
        final GitRepository repository = gitExecutorService.initRepository(dir);

        System.out.println(repository.getRepositoryName());

        final var branch = gitExecutorService.checkoutToMaster(repository);
        System.out.println(branch.getName());
        System.out.println(branch.getObjectId());
    }
}
