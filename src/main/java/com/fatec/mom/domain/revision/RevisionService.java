package com.fatec.mom.domain.revision;

import com.fatec.mom.domain.block.Block;
import com.fatec.mom.domain.block.BlockRepository;
import com.fatec.mom.domain.block.BlockStatus;
import com.fatec.mom.domain.document.Document;
import com.fatec.mom.domain.document.DocumentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Slf4j
public class RevisionService {

    private static final Integer FIRST_REVISION = 0;

    @Autowired
    private RevisionRepository revisionRepository;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private BlockRepository blockRepository;

    @Transactional
    public Revision saveNewRevision(Revision revision) {
        final Long documentId = revision.getDocument().getId();
        closeLastRevision(documentId);

        revision.setName(RevisionName.getRevName(getLastRevisionCode(documentId) + 1));
        revision.getBlocksInRevision().forEach(this::updateBlockStatus);

        return revisionRepository.save(revision);
    }

    @Transactional
    void updateBlockStatus(Block block) {
        block.setStatus(BlockStatus.IN_REVISION);
        blockRepository.save(block);
    }

    @Transactional
    Integer getLastRevisionCode(Long documentId) {
        final var lastNumber = revisionRepository.getLastRevisionCode(documentId);
        int revisionNumber = FIRST_REVISION;
        try {
            revisionNumber = Integer.parseInt(lastNumber);
        } catch (NumberFormatException e) {
            log.info("Nenhuma revisão encontrada");
        }
        return revisionNumber;
    }

    @Transactional
    void closeLastRevision(Long documentId) {
        revisionRepository.closeLastRevision(documentId);
    }

    @Transactional
    public Revision findByName(final String name) {
        return revisionRepository.findByName(name).orElseThrow();
    }

    @Transactional
    public Optional<Revision> findOpened(final Long documentId) {
        final var document = documentService.findById(documentId);
        if (document.isPresent()) {
            return revisionRepository.findByDocumentAndStatus(document.get(), RevisionStatus.OPENED);
        }

        return Optional.empty();
    }
}
