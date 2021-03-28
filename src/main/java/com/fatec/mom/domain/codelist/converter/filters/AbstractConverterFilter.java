package com.fatec.mom.domain.codelist.converter.filters;

import com.fatec.mom.domain.document.Document;
import com.fatec.mom.domain.file.FileInfo;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * A classe abstrata <code>AbstractConverterFilter</code> representa o modelo de Handler que converte os dados
 * do arquivo de codelist em objetos.
 *
 * @author Tobias Lino
 * @version v01 26/03/2021
 */
@Data
@Component
public abstract class AbstractConverterFilter {

    private AbstractConverterFilter next;

    public AbstractConverterFilter linkWith(final AbstractConverterFilter abstractConverterFilter) {
        this.next = abstractConverterFilter;
        return abstractConverterFilter;
    }

    public abstract List<Document> doFilter(final Document referenceDocument,
                                  final FileInfo fileInfo,
                                  final List<Document> documents) throws IOException;

    protected List<Document> doNextFilter(final Document referenceDocument,
                             final FileInfo fileInfo,
                             final List<Document> documents) throws IOException {
        if (next == null) return documents;
        return next.doFilter(referenceDocument, fileInfo, documents);
    }
}
