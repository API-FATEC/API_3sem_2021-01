package com.fatec.mom.domain.codelist;

import com.fatec.mom.domain.codelist.converter.filters.AbstractConverterFilter;
import com.fatec.mom.domain.document.Document;
import com.fatec.mom.domain.file.FileInfo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class CodelistConverterChain {

    @Getter @Setter
    private AbstractConverterFilter converterFilter;

    public void doFilters(Document referenceDocument, FileInfo fileInfo, List<Document> documents) throws IOException {
        converterFilter.doFilter(referenceDocument, fileInfo, documents);
    }
}
