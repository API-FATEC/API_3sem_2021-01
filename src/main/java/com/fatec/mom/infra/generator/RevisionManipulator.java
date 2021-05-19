package com.fatec.mom.infra.generator;

import com.fatec.mom.domain.document.DocumentDescriptor;
import com.fatec.mom.domain.revision.Revision;
import com.fatec.mom.infra.gitexecutor.GitExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RevisionManipulator {

    @Value("${default-documents-path}")
    private String documentsPath;

    private final GitExecutor gitExecutor;

    @Autowired
    public RevisionManipulator(GitExecutor gitExecutor) {
        this.gitExecutor = gitExecutor;
    }

    public void checkoutToRevision(final Revision revision) {
        final var revisionName = revision.getName();
        final var document = revision.getDocument();

        final var documentDescriptor = new DocumentDescriptor(
                String.format("%s/%s", documentsPath, document.getDocument()),
                document
        );

        tryCheckoutTo(revisionName, documentDescriptor);
    }

    public void checkoutToNewRevisionBranch(final Revision revision) {
        final var revisionName = revision.getName();
        final var document = revision.getDocument();

        final var documentDescriptor = new DocumentDescriptor(
                String.format("%s/%s", documentsPath, document.getDocument()),
                document
        );

        tryCheckoutToNewBranch(revisionName, documentDescriptor);
    }

    private void tryCheckoutTo(final String revisionName, final DocumentDescriptor descriptor) {
        if (gitExecutor.checkout(descriptor, revisionName)) {
            log.info("Checkout bem sucedido");
        }
    }

    private void tryCheckoutToNewBranch(final String revisionName, final DocumentDescriptor descriptor) {
        if (gitExecutor.checkoutNewBranch(descriptor, revisionName)) {
            log.info("Checkout bem sucedido");
        }
    }
}
