package com.fatec.mom.domain.block.pagescomparator.changes;

import com.fatec.mom.domain.block.BlockPage;

import java.util.List;
import java.util.Set;

public interface ChangeHandler {

    List<BlockPageChange> handleChanges(final List<BlockPageChange> changes);
}
