package com.fatec.mom.domain.block.pagescomparator.changes;

import com.fatec.mom.domain.block.BlockPage;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WhenPagesWereDeleted extends WhenPageChange {

    @Override
    public void handleChange(List<BlockPageChange> changes,
                             List<BlockPage> oldPages,
                             List<BlockPage> actualPages) {
        if (oldPages.size() > actualPages.size()){
            getChanges(oldPages, actualPages, oldPages.size(), (old, actual) -> {
                if (actual != null) {
                    if (old.getNumber() > actual.getNumber()) {
                        changes.add(BlockPageChange.builder()
                                .oldPage(old)
                                .revised(true)
                                .build());
                    }
                }
            });
        }
    }
}
