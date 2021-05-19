package com.fatec.mom.domain.block.pagescomparator;

import com.fatec.mom.domain.block.Block;
import com.fatec.mom.domain.block.pagescomparator.changes.BlockPageChange;
import com.fatec.mom.domain.block.pagescomparator.changes.PageChangeHandler;
import com.fatec.mom.domain.revision.Revision;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class BlockPageComparator {

    private final PageChangeHandler pageChangeHandler;

    public BlockPageComparator(PageChangeHandler pageChangeHandler) {
        this.pageChangeHandler = pageChangeHandler;
    }

    public List<BlockPageChange> getChanges(final Revision lastRevision, Block actual) {
        if (lastRevision.getBlocksInRevision().contains(actual)) {
            return pageChangeHandler.handleChanges(lastRevision, actual);
        }

        return Collections.emptyList();
    }
}
