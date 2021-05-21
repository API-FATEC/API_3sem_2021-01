package com.fatec.mom.infra.file.extractor.pagebuilder;

import com.fatec.mom.domain.block.Block;
import com.fatec.mom.domain.block.BlockPage;
import com.fatec.mom.domain.revision.Revision;
import com.fatec.mom.domain.revision.RevisionService;
import com.fatec.mom.infra.file.extractor.domain.page.PageContent;
import com.fatec.mom.infra.file.extractor.domain.page.PageData;
import com.fatec.mom.domain.revision.RevisionName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PageBuilder {

    private final RevisionService revisionService;

    @Autowired
    public PageBuilder(RevisionService revisionService) {
        this.revisionService = revisionService;
    }

    public BlockPage build(final Block block, final PageContent pageContent) {
        final Integer page = getPageNumber(pageContent);
        final String revisionName = RevisionName.getRevisionByString(
                pageContent.getData(PageData.PageDataType.REVISION).getData());

        final Revision revision = revisionService.findByNameAndDocument(revisionName, block.getDocument().getId());

        return BlockPage.builder()
                .number(page)
                .revision(revision)
                .block(block)
                .build();
    }

    private Integer getPageNumber(final PageContent pageContent) {
        final var data = pageContent.getData(PageData.PageDataType.PAGE).getData();
        int pageNumber = 0;
        try {
            pageNumber = Integer.parseInt(data);
        } catch (NumberFormatException e) {
            log.info("Nenhuma página encontrada");
        }
        return pageNumber;
    }
}
