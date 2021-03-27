package com.fatec.mom.domain.codelist.converter.filters;

import com.fatec.mom.domain.document.Document;
import com.fatec.mom.domain.document.DocumentRepository;
import com.fatec.mom.domain.file.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DocumentPersisterFilter extends AbstractConverterFilter {

    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public void doFilter(Document referenceDocument, FileInfo fileInfo, List<Document> documents) {
        var savedDocuments = documentRepository.saveAll(documents);

        doNextFilter(referenceDocument, fileInfo, savedDocuments);
    }
}
