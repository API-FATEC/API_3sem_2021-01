package com.fatec.mom.test.domain.document;

import com.fatec.mom.domain.document.Document;
import com.fatec.mom.domain.document.DocumentService;
import com.fatec.mom.test.integration.AbstractIntegrationTest;
import com.fatec.mom.test.integration.IntegrationTest;
import com.google.gson.Gson;
import org.json.JSONException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@IntegrationTest
class DocumentServiceTest extends AbstractIntegrationTest {

    @Autowired
    private DocumentService documentService;

    @Test
    @Sql(value = "/com/fatec/mom/test/sql/insert-three-documents-and-twenty-five-blocks.sql",
        config = @SqlConfig(transactionManager = "dataSourceTransactionManager"))
    void returnsAllDocumentsByNameAndPartNumber() throws JSONException {
        var docs = documentService.findByNameAndPartNumber("ABC", 1234);
        var json = new Gson().toJson(docs);

        JSONAssert.assertEquals(jsonAsString("expected-doc-search-result-as-list.json"), json, true);
    }

    @Test
    @Sql(value = "/com/fatec/mom/test/sql/insert-three-documents-and-twenty-five-blocks.sql",
        config = @SqlConfig(transactionManager = "dataSourceTransactionManager"))
    void returnsAllDocumentsByNameAndPartNumberAndTrait() throws JSONException {
        var doc = documentService.findByNameAndPartNumber("ABC", 1234);
        var json = new Gson().toJson(doc);

        JSONAssert.assertEquals(jsonAsString("expected-document-with-trait-50.json"), json, true);
    }


    @Test
    @Sql(value = "/com/fatec/mom/test/sql/docs-blocks-tables.sql",
        config = @SqlConfig(transactionManager = "dataSourceTransactionManager"))
    void shouldSaveAllDocumentsOfAList() throws ParseException, JSONException {
        var docs = simpleDocuments();
        var savedDocs = documentService.saveAll(docs);
        var json = new Gson().toJson(savedDocs);

        assertThat(savedDocs.size(), equalTo(3));
        JSONAssert.assertEquals(jsonAsString("expected-saved-docs-without-blocks.json"), json, true);
    }

    private List<Document> simpleDocuments() throws ParseException {
        var dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        var docs = new LinkedList<Document>();

//        docs.add(Document.builder().id(1L).createdDate(dateFormat.parse("23/06/2021")).name("test").partNumber(123).trait(50).blocks(new HashSet<>()).build());
//        docs.add(Document.builder().id(2L).createdDate(dateFormat.parse("23/06/2021")).name("test").partNumber(123).trait(55).blocks(new HashSet<>()).build());
//        docs.add(Document.builder().id(3L).createdDate(dateFormat.parse("23/06/2021")).name("test").partNumber(123).trait(60).blocks(new HashSet<>()).build());

        return docs;
    }

    @Test
    @DisplayName("Should save a doc and all the data inside him")
    @Sql(value = "/com/fatec/mom/test/sql/all-tables.sql",
            config = @SqlConfig(transactionManager = "dataSourceTransactionManager"))
    void shouldSaveADocAndAllTheDataInsideHim() {
        final var doc = DocumentBuilderAssistant.simpleDocument();
        final var result = documentService.save(doc);

        System.out.println(new Gson().toJson(result));
    }
}
