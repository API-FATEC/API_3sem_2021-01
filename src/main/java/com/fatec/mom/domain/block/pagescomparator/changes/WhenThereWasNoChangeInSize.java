package com.fatec.mom.domain.block.pagescomparator.changes;

import com.fatec.mom.domain.block.BlockPage;
import com.fatec.mom.domain.block.pagescomparator.BlockPageStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WhenThereWasNoChangeInSize extends WhenPageChange {

    @Override
    public void handleChange(List<BlockPageChange> changes,
                             List<BlockPage> oldPages,
                             List<BlockPage> actualPages) {
        getChanges(oldPages, actualPages, actualPages.size(), ((oldPage, actualPage) -> {
            var oldRevision = oldPage != null ? oldPage.getRevision().getName() : "";
            var actualRevision = oldPage != null ? actualPage.getRevision().getName() : "";

            if (!oldRevision.equalsIgnoreCase(actualRevision)) {
                changes.add(BlockPageChange.builder()
                        .actualPage(actualPage)
                        .oldPage(oldPage)
                        .revised(true)
                        .status(BlockPageStatus.NOT_CHANGED)
                        .build());
            } else {
                changes.add(BlockPageChange.builder()
                        .actualPage(actualPage)
                        .oldPage(oldPage)
                        .revised(false)
                        .status(BlockPageStatus.NOT_CHANGED)
                        .build());
            }
        }));
    }
}
