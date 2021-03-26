package com.fatec.mom.test.domain.document;

import com.fatec.mom.domain.document.DocumentRepository;
import com.fatec.mom.domain.document.DocumentSpecification;
import com.fatec.mom.domain.utils.JsonBuilder;
import com.fatec.mom.test.integration.AbstractIntegrationTest;
import com.fatec.mom.test.integration.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;

@IntegrationTest
public class DocumentSearchTest extends AbstractIntegrationTest {

    @Autowired
    private DocumentRepository documentRepository;

    @Test
    @Sql(value = "/com/fatec/mom/test/sql/insert-three-documents-and-twenty-five-blocks.sql")
    public void givenSomeDocumentsMustPerformANonSpecificSearchByName() {
        var docName = "AB";

        var result = documentRepository.findAll(DocumentSpecification.searchByName(docName));
        for (var i : result) {
            System.out.println(JsonBuilder.toJsonWithExcludeExpose(i));
        }
    }

    @Test
    @Sql(value = "/com/fatec/mom/test/sql/insert-three-documents-and-twenty-five-blocks.sql")
    @Transactional
    public void search() {
        var result = documentRepository.findAll();
        for (var i : result) {
            System.out.println(JsonBuilder.toJsonWithExcludeExpose(i));
        }
    }
}
