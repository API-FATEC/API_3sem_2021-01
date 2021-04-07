package com.fatec.mom.test.domain.document;

import com.fatec.mom.domain.document.DocumentService;
import com.fatec.mom.test.integration.AbstractIntegrationTest;
import com.fatec.mom.test.integration.IntegrationTest;
import com.google.gson.Gson;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

@IntegrationTest
public class DocumentServiceTest extends AbstractIntegrationTest {

    @Autowired
    private DocumentService documentService;

    @Test
    @Sql(value = "/com/fatec/mom/test/sql/insert-three-documents-and-twenty-five-blocks.sql",
        config = @SqlConfig(transactionManager = "dataSourceTransactionManager"))
    public void returnsAllDocumentsByNameAndPartNumber() throws JSONException {
        var docs = documentService.findAllByNameAndPartNumber("ABC", 1234);
        var json = new Gson().toJson(docs);

        JSONAssert.assertEquals(jsonAsString("expected-doc-search-result-as-list.json"), json, true);
    }

    @Test
    @Sql(value = "/com/fatec/mom/test/sql/insert-three-documents-and-twenty-five-blocks.sql",
        config = @SqlConfig(transactionManager = "dataSourceTransactionManager"))
    public void returnsAllDocumentsByNameAndPartNumberAndTrait() throws JSONException {
        var doc = documentService.findByNameAndPartNumberAndTrait("ABC", 1234, 50);
        var json = new Gson().toJson(doc);

        JSONAssert.assertEquals(jsonAsString("expected-document-with-trait-50.json"), json, true);
    }

}
