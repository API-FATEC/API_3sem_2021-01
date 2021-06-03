package com.fatec.mom.domain.revision;

import com.fatec.mom.domain.block.Block;
import com.fatec.mom.domain.block.BlockRepository;
import com.fatec.mom.domain.block.BlockService;
import com.fatec.mom.domain.block.BlockStatus;
import com.fatec.mom.domain.document.Document;
import com.fatec.mom.domain.document.DocumentService;
import com.fatec.mom.infra.generator.RevisionManipulator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.util.*;

@Service
@Slf4j
public class RevisionService {

    private static final Integer FIRST_REVISION = 0;

    @Autowired
    private RevisionRepository revisionRepository;

    @Autowired
    private RevisionTagRepository revisionTagRepository;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private BlockRepository blockRepository;

    @Autowired
    private RevisionManipulator revisionManipulator;

    @Transactional
    public Revision saveNewRevision(Revision revision) {
        final Long documentId = revision.getDocument().getId();

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
    public Integer getLastRevisionCode(Long documentId) {
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
    public Optional<Revision> closeLastRevision(Long documentId) {
        var openedRevision = Optional.ofNullable(revisionRepository.getOpenedRevision(documentId));

        if (openedRevision.isPresent()) {
            var revision = openedRevision.get();
            revision.setStatus(RevisionStatus.FINISHED);
            revision.setLastUpdateDate(new Date());
            tagRevision(revision);
            return Optional.of(revisionRepository.save(revision));
        }

        return Optional.empty();
    }

    private void tagRevision(final Revision revision) {
        final var tag = Optional.ofNullable(revisionTagRepository.getLastTagFrom(revision.getId()));
        final RevisionTag tagged;
        if (tag.isPresent()) {
            tagged = tryTagRevision(revision, tag.get());
        } else {
            tagged = tryTagRevision(revision, RevisionTag.builder().value("1.0").revision(revision).build());
        }
        revision.addRevisionTag(tagged);
    }

    private RevisionTag tryTagRevision(final Revision revision, final RevisionTag tag) {
        revisionManipulator.checkoutToRevision(revision);
        if (revisionManipulator.isIn(revision)) {
            return revisionManipulator.tagRevision(revision, tag);
        }
        return tag;
    }

    @Transactional
    public Revision findLastRevision(final Long documentId) {
        return revisionRepository.getLastRevision(documentId);
    }

    @Transactional
    public Optional<Revision> findOpened(final Long documentId) {
        final var document = documentService.findById(documentId);
        if (document.isPresent()) {
            return revisionRepository.findByDocumentAndStatus(document.get(), RevisionStatus.OPENED);
        }

        return Optional.empty();
    }

    @Transactional
    public Revision findByNameAndDocument(final String name, final Long documentId) {
        return revisionRepository.findByNameAndDocument_Id(name, documentId).orElseThrow();
    }

    @Transactional
    public Revision findById(final Long id) {
        return revisionRepository.findById(id).orElseThrow();
    }

    @Transactional
    public Revision saveOriginal(final Document document) {
        final var blocks = new LinkedList<>(document.getBlocks());
        var revision = Revision.builder()
                .document(document)
                .name(RevisionName.ORIGINAL.name())
                .blocksInRevision(blocks)
                .createdDate(new Date())
                .status(RevisionStatus.FINISHED)
                .build();

        return revisionRepository.save(revision);
    }

    @Transactional
    public void saveUpdateDate(final Revision revision) {
        revision.setLastUpdateDate(new Date());
        revisionRepository.save(revision);
    }

    //É pra retornar uma lista de blocks
    //findAllByStatus é um método que é pra pesquisar todos os blocos de acordo com o status
    @Transactional
    public List<Block> findBlocks(String documentName, Integer partNumber) {
        Document document = documentService.findByNameAndPartNumber(documentName, partNumber);
        for (Revision revisions : document.getRevisions()){
            if (revisions.getStatus().equals(RevisionStatus.OPENED)){
                return revisions.getBlocksInRevision();
            }
        }
        return null;
    }
}
