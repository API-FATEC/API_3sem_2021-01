package com.fatec.mom.test.infra.processor;

import com.fatec.mom.domain.document.Document;
import com.fatec.mom.domain.document.DocumentDescriptor;
import com.fatec.mom.infra.gitexecutor.GitImpl;
import com.fatec.mom.test.integration.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

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
        gitExecutor.getActualBranch(DocumentDescriptor.factory().createDescriptor(document));
    }
}
