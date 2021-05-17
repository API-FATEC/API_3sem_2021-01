package com.fatec.mom.infra.file.extractor.pagebuilder;

import com.fatec.mom.domain.block.Block;
import com.fatec.mom.domain.block.BlockPage;
import com.fatec.mom.infra.file.extractor.domain.page.PageContentsHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class PageBuilderInvoker {

    private final PageBuilder pageBuilder;

    @Autowired
    public PageBuilderInvoker(PageBuilder pageBuilder) {
        this.pageBuilder = pageBuilder;
    }

    public List<BlockPage> assemblePages(final Block block, final PageContentsHolder contentsHolder) {
        final List<BlockPage> blockPages = new LinkedList<>();

        contentsHolder.getContents().forEach(pageContent -> blockPages.add(pageBuilder.build(block, pageContent)));

        return blockPages;
    }
}
