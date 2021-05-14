package com.fatec.mom.infra.file.extractor.retrievers.content;

import com.fatec.mom.infra.file.extractor.SinglePageReader;
import com.fatec.mom.infra.file.extractor.domain.page.PageContent;
import com.fatec.mom.infra.file.extractor.domain.page.PageFooter;
import com.fatec.mom.infra.file.extractor.retrievers.FooterRetriever;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Component
public class MultiplePagesContentRetriever {

    private static final int INITIAL_INDEX = 0;

    private final FooterRetriever footerRetriever;

    private final SinglePageReader SinglePageReader;

    @Autowired
    public MultiplePagesContentRetriever(FooterRetriever footerRetriever,
                                         SinglePageReader SinglePageReader) {
        this.footerRetriever = footerRetriever;
        this.SinglePageReader = SinglePageReader;
    }

    public List<PageContent> getContents(final PDDocument document) throws IOException {
        final List<PageContent> contents = new LinkedList<>();
        for (int i = INITIAL_INDEX; i < document.getNumberOfPages(); ++i) {
            final PageFooter footer = footerRetriever.extractFooter(document.getPage(i));
            contents.add(new PageContent(SinglePageReader.extractPage(footer)));
        }

        return contents;
    }
}
