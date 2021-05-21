package com.fatec.mom.infra.generator;

import com.fatec.mom.domain.document.DocumentDescriptor;
import com.fatec.mom.domain.revision.Revision;
import com.fatec.mom.domain.revision.RevisionTag;
import com.fatec.mom.infra.gitexecutor.GitExecutor;
import com.fatec.mom.infra.gitexecutor.commit.DefaultMessage;
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
        final var documentDescriptor = getDescriptor(revision);

        tryCheckoutTo(revisionName, documentDescriptor);
    }

    public void checkoutToNewRevisionBranch(final Revision revision) {
        final var revisionName = revision.getName();
        final var documentDescriptor = getDescriptor(revision);

        tryCheckoutToNewBranch(revisionName, documentDescriptor);
    }

    private DocumentDescriptor getDescriptor(final Revision revision) {
        final var document = revision.getDocument();

        return new DocumentDescriptor(
                String.format("%s/%s", documentsPath, document.getDocument()),
                document
        );
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

    public void commitAllChanges(final Revision revision, final String blockName) {
        final var documentDescriptor = getDescriptor(revision);

        tryCommitAllChanges(blockName, documentDescriptor);
    }

    private void tryCommitAllChanges(final String message, final DocumentDescriptor documentDescriptor) {
        if (gitExecutor.addAll(documentDescriptor)) {
            tryCommit(documentDescriptor, message);
        } else {
            log.error("Não foi possível adicionar os arquivos");
        }
    }

    private void tryCommit(final DocumentDescriptor descriptor, final String message) {
        final var commit = gitExecutor.commit(descriptor,
                DefaultMessage.BLOCK_EDITED.getMessageBy(message));
        if (!commit) {
            log.error("Não foi possível realizar o commit das alterações");
        }
    }

    public boolean isIn(final Revision revision) {
        final var revisionName = revision.getName();
        final var documentDescriptor = getDescriptor(revision);

        final var branch = gitExecutor.getActualBranch(documentDescriptor);
        return branch.map(s -> s.equalsIgnoreCase(revisionName)).orElse(false);
    }

    public RevisionTag tagRevision(final Revision revision, final RevisionTag revisionTag) {
        final var revisionName = revision.getName();
        final var documentDescriptor = getDescriptor(revision);

        final boolean result = gitExecutor.tag(documentDescriptor,
                String.format("%.2f", revisionTag.incrementValue()),
                DefaultMessage.REVISION_CLOSED.getMessageBy(revisionName));
        if (!result) {
            log.error(String.format("Não foi possível realizar a tag %s para a revisão %s",
                    revisionTag.getValue(), revisionName));
        }

        return revisionTag;
    }
}
