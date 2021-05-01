package com.fatec.mom.test.domain.document;

import com.fatec.mom.domain.document.DocumentRepository;
import com.fatec.mom.test.integration.AbstractIntegrationTest;
import com.fatec.mom.test.integration.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@IntegrationTest
class DocumentRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    private DocumentRepository documentRepository;

    @Test
    @Sql(value = "/com/fatec/mom/test/sql/docs-blocks-tables.sql")
    @Transactional
    void givenADocumentWithASetOfBlocksShouldPersistAllObjects() {
        var document = DocumentBuilderAssistant.simpleDocument();

        var savedDocument = documentRepository.save(document);

        assertThat(savedDocument.getDocument(), equalTo("ABC-1234"));
    }
}
