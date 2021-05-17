package com.fatec.mom.domain.block.pagescomparator.changes;

import com.fatec.mom.domain.block.pagescomparator.BlockPageStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeletedPageHandler implements ChangeHandler {

    @Override
    public List<BlockPageChange> handleChanges(List<BlockPageChange> changes) {
        for (BlockPageChange change : changes) {
            if (change.isRevised() && change.getActualPage() == null) {
                change.setStatus(BlockPageStatus.DEL);
            }
        }

        return changes;
    }
}
