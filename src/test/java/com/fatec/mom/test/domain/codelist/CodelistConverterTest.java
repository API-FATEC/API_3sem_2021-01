package com.fatec.mom.test.domain.codelist;

import com.fatec.mom.domain.codelist.CodelistConverterService;
import com.fatec.mom.domain.document.Document;
import com.fatec.mom.domain.document.DocumentService;
import com.fatec.mom.domain.file.FileInfo;
import com.fatec.mom.domain.file.Reader;
import com.fatec.mom.test.integration.AbstractIntegrationTest;
import com.fatec.mom.test.integration.IntegrationTest;
import com.google.gson.Gson;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

@IntegrationTest
class CodelistConverterTest extends AbstractIntegrationTest {

    @Autowired
    private CodelistConverterService converterService;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private Reader xlsReader;

    @Test
    @Sql(value = "/com/fatec/mom/test/sql/docs-blocks-tables.sql")
    void convertAllCodelistFileDataIntoDocuments() throws IOException, JSONException, ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        var doc = Document.builder()
                .name("ABC")
                .partNumber(1234)
                .createdDate(simpleDateFormat.parse("2021-03-23 12:00:00"))
                .build();

        var fileInfo = FileInfo.builder()
                .fileName("Codelist.xlsx")
                .actualIndex(1)
                .build();

        fileInfo.setTotalRows(xlsReader.getSize(fileInfo.getFileName()));

        converterService.convertFileDataIntoDocuments(doc, fileInfo);

        var result = documentService.findAll();
        var json = new Gson().toJson(result);

        assertThat(result.size(), equalTo(3));
        assertThat(result.get(0).getBlocks().size(), equalTo(12));
        assertThat(result.get(1).getBlocks().size(), equalTo(11));
        assertThat(result.get(2).getBlocks().size(), equalTo(12));
        assertThat(result.get(0).getTrait(), equalTo(50));
        assertThat(result.get(1).getTrait(), equalTo(55));
        assertThat(result.get(2).getTrait(), equalTo(60));

        JSONAssert.assertEquals(jsonAsString("expected-inserted-docs.json"), json, true);
    }

}
