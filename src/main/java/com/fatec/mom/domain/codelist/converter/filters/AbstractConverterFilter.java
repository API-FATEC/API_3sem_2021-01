package com.fatec.mom.domain.codelist.converter.filters;

import com.fatec.mom.domain.document.Document;
import com.fatec.mom.domain.file.FileInfo;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Data
@Component
public abstract class AbstractConverterFilter {

    private AbstractConverterFilter next;

    public AbstractConverterFilter linkWith(final AbstractConverterFilter abstractConverterFilter) {
        this.next = abstractConverterFilter;
        return abstractConverterFilter;
    }

    public abstract void doFilter(final Document referenceDocument,
                                  final FileInfo fileInfo,
                                  final List<Document> documents) throws IOException;

    protected void doNextFilter(final Document referenceDocument,
                             final FileInfo fileInfo,
                             final List<Document> documents) throws IOException {
        if (next == null) return;
        next.doFilter(referenceDocument, fileInfo, documents);
    }
}
