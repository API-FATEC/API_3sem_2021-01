package com.fatec.mom.infra.file.extractor.pagebuilder;

import com.fatec.mom.domain.block.Block;
import com.fatec.mom.domain.block.BlockPage;
import com.fatec.mom.domain.revision.Revision;
import com.fatec.mom.domain.revision.RevisionService;
import com.fatec.mom.infra.file.extractor.domain.page.PageContent;
import com.fatec.mom.infra.file.extractor.domain.page.PageData;
import com.fatec.mom.domain.revision.RevisionName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PageBuilder {

    private final RevisionService revisionService;

    @Autowired
    public PageBuilder(RevisionService revisionService) {
        this.revisionService = revisionService;
    }

    public BlockPage build(final Block block, final PageContent pageContent) {
        final Integer page = Integer.parseInt(pageContent.getData(PageData.PageDataType.PAGE).getData());
        final String revisionName = RevisionName.getRevisionByString(
                pageContent.getData(PageData.PageDataType.REVISION).getData());

//        final Revision revision = revisionService.findByName(revisionName);

        return BlockPage.builder()
                .number(page)
//                .revision(revision)
                .block(block)
                .build();
    }
}
