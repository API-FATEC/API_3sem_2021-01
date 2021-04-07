package com.fatec.mom.domain.codelist.converter.filters;

import com.fatec.mom.domain.document.Document;
import com.fatec.mom.domain.document.DocumentDeserializer;
import com.fatec.mom.domain.file.FileInfo;
import com.fatec.mom.domain.file.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * A classe <code>DocumentConverterFilter</code> é responsável por obter todos os traços definidos no arquivo de
 * codelist e convertê-los em objetos do tipo <code>Document</code>, utilizando o <code>referenceDocument</code>
 * para criar os documentos corretos.
 *
 * @author Tobias Lino
 * @version v01 26/03/2021
 */
@Component
public class DocumentConverterFilter extends AbstractConverterFilter {

    @Autowired
    private Reader xlsFileReader;

    @Override
    public List<Document> doFilter(Document referenceDocument, FileInfo fileInfo, List<Document> documents) throws IOException {
        final var fileName = fileInfo.getFileName();

        var firstLine = xlsFileReader.getRow(fileName, fileInfo.getActualIndex());
        var deserializer = new DocumentDeserializer(referenceDocument.getName(), referenceDocument.getPartNumber(), referenceDocument.getCreatedDate());
        var convertedDocuments = deserializer.deserialize(firstLine);

        fileInfo.setActualIndex(2);
        return doNextFilter(referenceDocument, fileInfo, convertedDocuments);
    }
}
