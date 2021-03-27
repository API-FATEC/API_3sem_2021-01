package com.fatec.mom.test.domain.codelist;

import com.fatec.mom.domain.codelist.CodelistConverterService;
import com.fatec.mom.domain.document.Document;
import com.fatec.mom.domain.document.DocumentRepository;
import com.fatec.mom.domain.file.FileInfo;
import com.fatec.mom.domain.file.Reader;
import com.fatec.mom.domain.utils.JsonBuilder;
import com.fatec.mom.test.integration.AbstractIntegrationTest;
import com.fatec.mom.test.integration.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.io.IOException;

@IntegrationTest
public class CodelistConverterTest extends AbstractIntegrationTest {

    @Autowired
    private CodelistConverterService converterService;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private Reader xlsReader;

    @Test
    @Sql(value = "/com/fatec/mom/test/sql/docs-blocks-tables.sql")
    public void convertAllCodelistFileDataIntoDocuments() throws IOException {
        var doc = Document.builder()
                .name("ABC")
                .partNumber(1234).build();

        var fileInfo = FileInfo.builder()
                .fileName("Codelist.xlsx")
                .actualIndex(1)
                .build();

        fileInfo.setTotalRows(xlsReader.getSize(fileInfo.getFileName()));

        converterService.convertFileDataIntoDocuments(doc, fileInfo);

        var result = documentRepository.findAll();
        for (var i : result) {
            System.out.println(JsonBuilder.toJsonWithExcludeExpose(i));
        }
    }

}
