package com.fatec.mom.test.domain.codelist;

import com.fatec.mom.domain.document.Document;
import com.fatec.mom.domain.document.DocumentService;
import com.fatec.mom.infra.codelist.reader.CodelistReader;
import com.fatec.mom.infra.codelist.reader.CodelistReaderLocator;
import com.fatec.mom.infra.codelist.reader.CodelistReaderType;
import com.fatec.mom.infra.codelist.reader.config.CodelistConfigType;
import com.fatec.mom.test.integration.IntegrationTest;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import javax.annotation.Resource;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@IntegrationTest
public class CodelistImporterTest {

    @Resource
    private CodelistReaderLocator locator;

    @Autowired
    private DocumentService documentService;

    @Nested
    @DisplayName("Run")
    class Run {

        @Test
        @DisplayName("single tab import")
        @Sql(value = "/com/fatec/mom/test/sql/all-tables.sql")
        void runSingleTabImport() {
            final var stream = getClass().getResourceAsStream("/com/fatec/mom/test/documents/Codelist.xlsx");
            final var reader = locator.getReader(CodelistReaderType.SINGLE_TAB);
            final var documents = reader.readCodelist(stream);

            assertThat(documents.size(), equalTo(1));

            final var result = documentService.saveAll(documents);
            assertThat(result.get(0).getDocument(), equalTo("ABC-1234"));
            System.out.println(new Gson().toJson(result));
        }

        @Test
        @DisplayName("multiple tab import")
        @Sql(value = "/com/fatec/mom/test/sql/all-tables.sql")
        void runMultipleTabImport() {
            final var stream = getClass().getResourceAsStream("/com/fatec/mom/test/documents/MultipleTabCodelist.xlsx");
            final var reader = locator.getReader(CodelistReaderType.MULTIPLE_TAB);
            final var documents = reader.readCodelist(stream);

            assertThat(documents.size(), equalTo(3));

            final var result = documentService.saveAll(documents);

            assertThat(result.get(0).getDocument(), equalTo("ABC-1234"));
            System.out.println(new Gson().toJson(result));
        }
    }
}
