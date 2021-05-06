package com.fatec.mom.test.infra.processor;

import com.fatec.mom.domain.document.Document;
import com.fatec.mom.domain.document.DocumentDescriptor;
import com.fatec.mom.infra.gitexecutor.GitImpl;
import com.fatec.mom.infra.gitexecutor.commands.GitCommandResult;
import com.fatec.mom.test.integration.IntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

@IntegrationTest
public class GitExecutorProcessTest {

    @Autowired
    private GitImpl gitExecutor;

    @Test
    void getActualBranch() throws IOException {
        final Document document = Document.builder()
                .name("ABC")
                .partNumber(1234)
                .build();
        final GitCommandResult result = gitExecutor.getActualBranch(DocumentDescriptor.factory().createDescriptor(document)).orElseThrow();
        if (result.success()) {
            result.getOutput().forEach(System.out::println);
        }
    }

    @Test
    @DisplayName("makes an add of the files")
    void makesAnAddOfTheFiles() {

        fail("Not implemented");
    }
}
