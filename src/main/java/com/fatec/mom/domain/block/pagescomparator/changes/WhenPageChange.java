package com.fatec.mom.domain.block.pagescomparator.changes;

import com.fatec.mom.domain.block.BlockPage;

import java.util.List;
import java.util.function.BiConsumer;

public abstract class WhenPageChange {

    public abstract void handleChange(final  List<BlockPageChange>  changes,
                                      final List<BlockPage> oldPages,
                                      final List<BlockPage> actualPages);

    protected void getChanges(final List<BlockPage> oldPages,
                            final List<BlockPage> actualPages,
                            final int size,
                            final BiConsumer<BlockPage, BlockPage> consumer) {
        for (int i = 0; i < size; ++i) {
            var oldPage = extractPage(oldPages, i);
            var actualPage = extractPage(actualPages, i);

            consumer.accept(oldPage, actualPage);
        }
    }

    private BlockPage extractPage(List<BlockPage> pages, int index) {
        BlockPage page;
        try {
            page = pages.get(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            page = null;
        }
        return page;
    }
}
