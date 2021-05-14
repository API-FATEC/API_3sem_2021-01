package com.fatec.mom.infra.file.extractor;

import com.fatec.mom.infra.file.extractor.domain.page.PageContent;
import com.fatec.mom.infra.file.extractor.retrievers.content.MultiplePagesContentRetriever;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.io.InputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
@Slf4j
public class PageReader {

    private final MultiplePagesContentRetriever contentRetriever;

    @Autowired
    public PageReader(MultiplePagesContentRetriever contentRetriever) {
        this.contentRetriever = contentRetriever;
    }

    public List<PageContent> readPages(@NotNull InputStream inputStream) throws IOException {
        PDDocument document = new PDDocument();

        try {
            document = PDDocument.load(inputStream);
            return contentRetriever.getContents(document);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Fail Loading File");
        } finally {
            document.close();
        }


        return Collections.emptyList();
    }
}
