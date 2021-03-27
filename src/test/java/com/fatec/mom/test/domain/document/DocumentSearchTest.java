package com.fatec.mom.test.domain.document;

import com.fatec.mom.domain.document.DocumentRepository;
import com.fatec.mom.domain.document.DocumentSpecification;
import com.fatec.mom.test.integration.AbstractIntegrationTest;
import com.fatec.mom.test.integration.IntegrationTest;
import com.google.gson.Gson;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;

@IntegrationTest
public class DocumentSearchTest extends AbstractIntegrationTest {

    @Autowired
    private DocumentRepository documentRepository;

    @Test
    @Sql(value = "/com/fatec/mom/test/sql/insert-three-documents-and-twenty-five-blocks.sql")
    @Transactional
    public void givenSomeDocumentsMustPerformANonSpecificSearchByName() throws JSONException {
        var docName = "AB";

        var result = documentRepository.findAll(DocumentSpecification.searchByName(docName));
        var json = new Gson().toJson(result);

        JSONAssert.assertEquals(jsonAsString("expected-doc-search-result-as-list.json"), json, true);
    }

    @Test
    @Sql(value = "/com/fatec/mom/test/sql/insert-three-documents-and-twenty-five-blocks.sql")
    @Transactional
    public void searchAllDocuments() throws JSONException {
        var result = documentRepository.findAll();
        var json = new Gson().toJson(result);

        JSONAssert.assertEquals(jsonAsString("expected-doc-search-result-as-list.json"), json, true);
    }
}
