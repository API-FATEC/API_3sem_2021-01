package com.fatec.mom.domain.block.pagescomparator.changes;

import com.fatec.mom.domain.block.pagescomparator.BlockPageStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NewPageHandler implements ChangeHandler {

    @Override
    public List<BlockPageChange> handleChanges(List<BlockPageChange> changes) {
        for (BlockPageChange change : changes) {
//            if (change.isRevised() && change.getOldPage() == null) {
//                change.setStatus(BlockPageStatus.NEW);
//            }
        }

        return changes;
    }
}
