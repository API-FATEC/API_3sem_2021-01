package com.fatec.mom.test.infra.gitexecutor;

import com.fatec.mom.domain.document.Document;
import com.fatec.mom.domain.document.DocumentDescriptor;
import com.fatec.mom.infra.gitexecutor.GitExecutor;
import com.fatec.mom.test.integration.IntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@IntegrationTest
public class GitExecutorTest {

    @Autowired
    GitExecutor gitExecutor;

    @Test
    @DisplayName("get Actual Branch as Optional<String>")
    void getActualBranchAsOptionalString() {
        final String actualBranchABC = gitExecutor.getActualBranch(simpleDescriptor("ABC", 1234)).orElseThrow();
        final String actualBranchAAA = gitExecutor.getActualBranch(simpleDescriptor("AAA", 1234)).orElseThrow();

        assertThat(actualBranchABC, equalTo("master"));
        assertThat(actualBranchAAA, equalTo("test"));
    }

    @Test
    @DisplayName("Try checkout To Master")
    void tryCheckoutToMaster() {
        final var resultABC = gitExecutor.checkoutToMaster(simpleDescriptor("ABC", 1234));
        final var resultAAA = gitExecutor.checkoutToMaster(simpleDescriptor("AAA", 1234));
        assertThat(resultABC, equalTo(true));
        assertThat(resultAAA, equalTo(true));

        final String actualBranchAAA = gitExecutor.getActualBranch(simpleDescriptor("AAA", 1234)).orElseThrow();
        assertThat(actualBranchAAA, equalTo("master"));
    }

    DocumentDescriptor simpleDescriptor(@NotNull String documentName,
                                        @NotNull Integer partNumber) {
        final Document document = Document.builder()
                .name(documentName)
                .partNumber(partNumber)
                .build();
        return DocumentDescriptor.factory().createDescriptor(document);
    }
}
