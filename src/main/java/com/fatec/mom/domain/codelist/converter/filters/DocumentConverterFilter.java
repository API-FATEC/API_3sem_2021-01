package com.fatec.mom.domain.codelist.converter.filters;

import com.fatec.mom.domain.document.Document;
import com.fatec.mom.domain.document.DocumentDeserializer;
import com.fatec.mom.domain.file.FileInfo;
import com.fatec.mom.domain.file.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class DocumentConverterFilter extends AbstractConverterFilter {

    @Autowired
    private Reader xlsFileReader;

    @Override
    public void doFilter(Document referenceDocument, FileInfo fileInfo, List<Document> documents) throws IOException {
        final var fileName = fileInfo.getFileName();

        var firstLine = xlsFileReader.getRow(fileName, fileInfo.getActualIndex());
        var deserializer = new DocumentDeserializer(referenceDocument.getName(), referenceDocument.getPartNumber());
        var convertedDocuments = deserializer.deserializeAll(firstLine);

        fileInfo.setActualIndex(2);
        doNextFilter(referenceDocument, fileInfo, convertedDocuments);
    }
}
