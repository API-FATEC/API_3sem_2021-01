package com.fatec.mom.test.domain.document;

import com.fatec.mom.domain.document.DocumentRepository;
import com.fatec.mom.test.integration.IntegrationTest;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import javax.transaction.Transactional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@IntegrationTest
class DocumentMappingTest {

    @Autowired
    DocumentRepository repository;

    @Nested
    @DisplayName("Should")
    class Should {

        @Test
        @DisplayName("save all documents")
        @Sql(value = "/com/fatec/mom/test/sql/all-tables.sql",
            config = @SqlConfig(transactionManager = "dataSourceTransactionManager"))
        @Transactional
        void saveAllDocuments() {
            final var doc = DocumentBuilderAssistant.simpleDocument();
            final var result = repository.save(doc);

            System.out.println(result);
        }
    }

}
