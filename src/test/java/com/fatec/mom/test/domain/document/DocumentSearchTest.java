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
class DocumentSearchTest extends AbstractIntegrationTest {

    @Autowired
    private DocumentRepository documentRepository;

    @Test
    @Sql(value = "/com/fatec/mom/test/sql/insert-three-documents-and-twenty-five-blocks.sql")
    @Transactional
    void givenSomeDocumentsMustPerformANonSpecificSearchByName() throws JSONException {
        var docName = "AB";

        var result = documentRepository.findAll(DocumentSpecification.searchByName(docName));
        var json = new Gson().toJson(result);
        System.out.println(json);

        JSONAssert.assertEquals(jsonAsString("expected-doc-search-result-as-list.json"), json, true);
    }

    @Test
    @Sql(value = "/com/fatec/mom/test/sql/insert-three-documents-and-twenty-five-blocks.sql")
    @Transactional
    void givenSomeDocumentsMustPerformANonSpecificSearchByPartNumber() throws JSONException {
        var partNumber = 1234;

        var result = documentRepository.findAll(DocumentSpecification.searchByPartNumber(partNumber));
        var json = new Gson().toJson(result);
        System.out.println(json);

        JSONAssert.assertEquals(jsonAsString("expected-doc-search-result-as-list.json"), json, true);
    }

    @Test
    @Sql(value = "/com/fatec/mom/test/sql/insert-three-documents-and-twenty-five-blocks.sql")
    @Transactional
    void givenSomeDocumentsMustPerformANonSpecificSearchByTrait() throws JSONException {
        var trait = 50;

        var result = documentRepository.findAll(DocumentSpecification.searchByTrait(trait));
        var json = new Gson().toJson(result);
        System.out.println(json);

        JSONAssert.assertEquals(jsonAsString("expected-document-with-trait-50-as-list.json"), json, true);
    }

    @Test
    @Sql(value = "/com/fatec/mom/test/sql/insert-three-documents-and-twenty-five-blocks.sql")
    @Transactional
    void searchAllDocuments() throws JSONException {
        var result = documentRepository.findAll();
        var json = new Gson().toJson(result);

        JSONAssert.assertEquals(jsonAsString("expected-doc-search-result-as-list.json"), json, true);
    }
}
