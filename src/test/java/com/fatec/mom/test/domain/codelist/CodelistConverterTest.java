package com.fatec.mom.test.domain.codelist;

import com.fatec.mom.domain.codelist.CodelistConverterService;
import com.fatec.mom.domain.document.Document;
import com.fatec.mom.domain.document.DocumentRepository;
import com.fatec.mom.domain.file.FileInfo;
import com.fatec.mom.domain.utils.JsonBuilder;
import com.fatec.mom.test.integration.AbstractIntegrationTest;
import com.fatec.mom.test.integration.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

@IntegrationTest
public class CodelistConverterTest extends AbstractIntegrationTest {

    @Autowired
    private CodelistConverterService converterService;

    @Autowired
    private DocumentRepository documentRepository;

    @Test
    @Sql(value = "/com/fatec/mom/test/sql/docs-blocks-tables.sql")
    public void test01() {
        var doc = Document.builder()
                .name("ABC")
                .partNumber(1234).build();

        var fileInfo = FileInfo.builder()
                .fileName("Codelist.xlsx")
                .path("/com/fatec/mom/test/mockup/")
                .actualIndex(1)
                .totalRows(28)
                .build();

        converterService.convertFileDataIntoDocuments(doc, fileInfo);

        var result = documentRepository.findAll();
        for (var i : result) {
            System.out.println(JsonBuilder.toJsonWithExcludeExpose(i));
        }
    }

}
