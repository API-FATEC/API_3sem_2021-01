package com.fatec.mom.infra.file.extractor;

import com.fatec.mom.infra.file.extractor.domain.page.PageData;
import com.fatec.mom.infra.file.extractor.domain.page.PageFooter;
import com.fatec.mom.infra.file.extractor.retrievers.DataRetriever;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class SinglePageReader {

    private final List<DataRetriever> retrievers;

    public SinglePageReader(@Qualifier("dataRetrievers") List<DataRetriever> retrievers) {
        this.retrievers = retrievers;
    }


    public List<PageData> extractPage(PageFooter footer) {
        final List<PageData> pages = new LinkedList<>();

        retrievers.forEach(dataRetriever -> pages.add(dataRetriever.retrieveData(footer.getData())));

        return pages;
    }
}
