package com.fatec.mom.domain.block.pagescomparator;

import com.fatec.mom.domain.block.Block;
import com.fatec.mom.domain.block.BlockPage;
import com.fatec.mom.domain.block.pagescomparator.changes.BlockPageChange;
import com.fatec.mom.domain.block.pagescomparator.changes.ChangeHandler;
import com.fatec.mom.domain.block.pagescomparator.changes.RevisedPageHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class BlockPageComparator {

    private final RevisedPageHandler revisedPageHandler;

    private final List<ChangeHandler> changeHandlers;


    public BlockPageComparator(RevisedPageHandler revisedPageHandler,
                               @Qualifier("changeHandlers") List<ChangeHandler> changeHandlers) {
        this.revisedPageHandler = revisedPageHandler;
        this.changeHandlers = changeHandlers;
    }

    public List<BlockPageChange> getChanges(Block old, Block actual) {
        final Set<BlockPage> oldPages = old.getPages();
        final Set<BlockPage> actualPages = actual.getPages();

        return compareChanges(oldPages, actualPages);
    }

    private List<BlockPageChange> compareChanges(Set<BlockPage> oldPages, Set<BlockPage> actualPages) {
        List<BlockPageChange> changes = revisedPageHandler.handleChanges(oldPages, actualPages);

        for (ChangeHandler handler : changeHandlers) {
            handler.handleChanges(changes);
        }

        return changes;
    }
}
