package com.fatec.mom.test.domain.block;

import com.fatec.mom.domain.block.Block;
import com.fatec.mom.domain.block.BlockPage;
import com.fatec.mom.domain.block.BlockStatus;
import com.fatec.mom.domain.block.pagescomparator.BlockPageComparator;
import com.fatec.mom.domain.block.pagescomparator.changes.BlockPageChange;
import com.fatec.mom.domain.document.Document;
import com.fatec.mom.domain.document.DocumentService;
import com.fatec.mom.test.domain.revision.RevisionBuilderAssistant;
import com.fatec.mom.test.integration.IntegrationTest;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@IntegrationTest
public class BlockComparatorTest {

    @Autowired
    DocumentService documentService;

    @Autowired
    BlockPageComparator comparator;

    @Test
    @DisplayName("Compares Differences Between Two Blocks")
    @Sql(value = "/com/fatec/mom/test/sql/all-tables.sql")
    void comparesDifferencesBetweenTwoBlocks() {
        final Block old = simpleBlock();
        old.setPages(originalPages(old));

        final Block revised = simpleBlock();
        revised.setPages(newPages(revised));

//        final var changes = comparator.getChanges(old, revised);

//        var teste = changes.stream().filter(BlockPageChange::isRevised).collect(Collectors.toList());
//        System.out.println(changes);
    }

    private Block simpleBlock() {
        return Block.builder()
                .status(BlockStatus.CLOSED)
                .name("Teste 01")
                .order(1)
                .code(14)
                .section("01")
                .subSection("02")
                .code(3)
                .build();
    }

    private Set<BlockPage> originalPages(final Block block) {
        final var original = RevisionBuilderAssistant.originalFromBlock(block);
        final var firstRevision = RevisionBuilderAssistant.simpleFinishedFromBlock(1, block);
        return new HashSet<>() {{
            add(BlockPage.builder().block(block).revision(original).number(1).build());
            add(BlockPage.builder().block(block).revision(firstRevision).number(2).build());
            add(BlockPage.builder().block(block).revision(original).number(3).build());
            add(BlockPage.builder().block(block).revision(firstRevision).number(4).build());
        }};
    }

    private Set<BlockPage> newPages(final Block block) {
        final var firstRevision = RevisionBuilderAssistant.simpleFinishedFromBlock(1, block);
        final var openedRevision = RevisionBuilderAssistant.simpleOpenedFromBlock(2, block);
        return new HashSet<>() {{
            add(BlockPage.builder().block(block).revision(openedRevision).number(1).build());
            add(BlockPage.builder().block(block).revision(firstRevision).number(2).build());
            add(BlockPage.builder().block(block).revision(openedRevision).number(3).build());
            add(BlockPage.builder().block(block).revision(openedRevision).number(4).build());
            add(BlockPage.builder().block(block).revision(openedRevision).number(5).build());
        }};

    }
}
