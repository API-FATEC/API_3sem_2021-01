package com.fatec.mom.domain.block.pagescomparator.changes;

import com.fatec.mom.domain.block.Block;
import com.fatec.mom.domain.block.BlockPage;
import com.fatec.mom.domain.revision.Revision;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PageChangeHandler {

    private final List<ChangeHandler> changeHandlers;

    public PageChangeHandler(@Qualifier("changeHandlers") List<ChangeHandler> changeHandlers) {
        this.changeHandlers = changeHandlers;
    }

    public List<BlockPageChange> handleChanges(final Revision lastRevision, final Block block) {
        final List<BlockPageChange> changes = new LinkedList<>();
        final var pages = block.getPages();

        pages.forEach(page -> {
            final var change = handlePageChange(lastRevision.getName(), page);
            changes.add(change);
        });

        changeHandlers.forEach(handler -> handler.handleChanges(changes));

        return changes;
    }

    private BlockPageChange handlePageChange(final String lastRevisionName, final BlockPage page) {
        final var actualPageRevision = page.getRevision().getName();

        return actualPageRevision.equalsIgnoreCase(lastRevisionName) ?
                BlockPageChange.createRevised(page) :
                BlockPageChange.createNonRevised(page);
    }
}
