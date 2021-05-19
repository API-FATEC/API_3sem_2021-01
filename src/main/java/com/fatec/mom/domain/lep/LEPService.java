package com.fatec.mom.domain.lep;

import com.fatec.mom.domain.block.Block;
import com.fatec.mom.domain.block.BlockPageChangesService;
import com.fatec.mom.domain.block.pagescomparator.BlockPageComparator;
import com.fatec.mom.domain.block.pagescomparator.changes.BlockPageChange;
import com.fatec.mom.domain.document.Document;
import com.fatec.mom.domain.document.DocumentService;
import com.fatec.mom.domain.revision.Revision;
import com.fatec.mom.domain.revision.RevisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class LEPService {

    private final BlockPageChangesService blockPageChangesService;

    private final DocumentService documentService;

    @Autowired
    private BlockPageComparator blockPageComparator;

    @Autowired
    private RevisionService revisionService;

    @Autowired
    public LEPService(BlockPageChangesService blockPageChangesService,
                      DocumentService documentService) {
        this.blockPageChangesService = blockPageChangesService;
        this.documentService = documentService;
    }

    @Transactional
    public LEP getLEP(final Document document) {
        final var sortedBlocks = document.getBlocks().stream()
                .sorted(Comparator.comparing(Block::getOrder))
                .collect(Collectors.toList());

        final var pagesChanges = getAllChanges(sortedBlocks);

        return new LEP(document.getRevisions(), pagesChanges);
    }

    @Transactional
    public List<BlockPageChange> getAllChanges(final List<Block> blocks) {
        final var changes = new LinkedList<BlockPageChange>();

        blocks.forEach(block -> {
            final var pageChanges = blockPageChangesService.getAllChangesFrom(block);
            changes.addAll(pageChanges);
        });

        return changes;
    }

    @Transactional
    public LEP getLEP(final Long documentId) {
        final var document = documentService.findById(documentId);
        if (document.isEmpty()) {
            return new LEP();
        }

        return getLEP(document.get());
    }

    @Transactional
    public LEP compareChanges(final Long documentId) {
        final var document = documentService.findById(documentId);
        if (document.isEmpty()) {
            return new LEP();
        }

        final var doc = document.get();
        final var lastRevision = revisionService.findLastRevision(documentId);
        var pages = compareChanges(lastRevision, doc.getBlocks());
        pages = blockPageChangesService.saveAll(pages);

        return new LEP(doc.getRevisions(), pages);
    }

    private List<BlockPageChange> compareChanges(final Revision revision, final Set<Block> blocks) {
        if (blocks.isEmpty()) {
            return Collections.emptyList();
        }

        final var changes = new LinkedList<BlockPageChange>();
        blocks.forEach(block -> changes.addAll(blockPageComparator.getChanges(revision, block)));

        return changes;
    }
}
