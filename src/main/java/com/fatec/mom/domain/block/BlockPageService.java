package com.fatec.mom.domain.block;

import com.fatec.mom.domain.block.pagescomparator.BlockPageComparator;
import com.fatec.mom.domain.block.pagescomparator.changes.BlockPageChange;
import com.fatec.mom.domain.revision.Revision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
public class BlockPageService {

    private final BlockPageComparator pageComparator;

    @Autowired
    public BlockPageService(BlockPageComparator pageComparator) {
        this.pageComparator = pageComparator;
    }

    public List<BlockPageChange> getPages(final Revision lastRevision, final Set<Block> blocks) {
        if (blocks.isEmpty()) {
            return Collections.emptyList();
        }

        final var changes = new LinkedList<BlockPageChange>();
        blocks.forEach(block -> changes.addAll(pageComparator.getChanges(lastRevision, block)));

        return changes;
    }
}
