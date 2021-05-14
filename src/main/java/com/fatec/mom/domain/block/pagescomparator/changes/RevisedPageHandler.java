package com.fatec.mom.domain.block.pagescomparator.changes;

import com.fatec.mom.domain.block.BlockPage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Component
public class RevisedPageHandler {

    private final List<WhenPageChange> whenPageChangesHandler;

    public RevisedPageHandler(@Qualifier("whenPageChangesHandler") List<WhenPageChange> whenPageChangesHandler) {
        this.whenPageChangesHandler = whenPageChangesHandler;
    }

    public List<BlockPageChange> handleChanges(Set<BlockPage> oldPages, Set<BlockPage> actualPages) {
        final List<BlockPageChange> changes = new LinkedList<>();

        var oldPagesList = new java.util.ArrayList<>(List.copyOf(oldPages));
        var actualPagesList = new java.util.ArrayList<>(List.copyOf(actualPages));

        oldPagesList.sort(Comparator.comparing(BlockPage::getNumber));
        actualPagesList.sort(Comparator.comparing(BlockPage::getNumber));

        for (WhenPageChange handler : whenPageChangesHandler) {
            handler.handleChange(changes, oldPagesList, actualPagesList);
        }

        return changes;
    }
}
