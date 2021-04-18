package com.fatec.mom.test.domain.codelist;

import com.fatec.mom.domain.document.Document;
import com.fatec.mom.domain.document.DocumentService;
import com.fatec.mom.infra.codelist.reader.CodelistReader;
import com.fatec.mom.infra.codelist.reader.CodelistReaderLocator;
import com.fatec.mom.infra.codelist.reader.CodelistReaderType;
import com.fatec.mom.infra.codelist.reader.config.CodelistConfigType;
import com.fatec.mom.test.integration.IntegrationTest;
import com.google.gson.Gson;
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

    @Test
    @Sql(value = "/com/fatec/mom/test/sql/docs-blocks-tables.sql")
    void runSingleTabImport() {
        final var stream = getClass().getResourceAsStream("/com/fatec/mom/test/documents/Codelist.xlsx");
        final var reader = locator.getReader(CodelistReaderType.SINGLE_TAB);
        final var documents = reader.readCodelist(CodelistConfigType.DEFAULT, stream);

        final var result = documentService.saveAll(documents);

        assertThat(result.size(), equalTo(3));

        assertThat(result.get(0).getName(), equalTo("ABC"));
        assertThat(result.get(0).getPartNumber(), equalTo(1234));

        assertThat(result.get(0).getTrait(), equalTo(50));
        assertThat(result.get(1).getTrait(), equalTo(55));
        assertThat(result.get(2).getTrait(), equalTo(60));

        assertThat(result.get(0).getBlocks().size(), equalTo(12));
        assertThat(result.get(1).getBlocks().size(), equalTo(11));
        assertThat(result.get(2).getBlocks().size(), equalTo(12));
    }

    @Test
    @Sql(value = "/com/fatec/mom/test/sql/docs-blocks-tables.sql")
    void runMultipleTabImport() {
        final var stream = getClass().getResourceAsStream("/com/fatec/mom/test/documents/MultipleTabCodelist.xlsx");
        final var reader = locator.getReader(CodelistReaderType.MULTIPLE_TAB);
        final var documents = reader.readCodelist(CodelistConfigType.DEFAULT, stream);

        final var result = documentService.saveAll(documents);

        assertThat(result.size(), equalTo(9));

        assertThat(result.get(0).getName(), equalTo("ABC"));
        assertThat(result.get(3).getName(), equalTo("AAA"));
        assertThat(result.get(6).getName(), equalTo("BBB"));

        assertThat(result.get(0).getPartNumber(), equalTo(1234));
        assertThat(result.get(3).getPartNumber(), equalTo(1111));
        assertThat(result.get(6).getPartNumber(), equalTo(1234));

        assertThat(result.get(0).getBlocks().size(), equalTo(12));
        assertThat(result.get(1).getBlocks().size(), equalTo(11));
        assertThat(result.get(2).getBlocks().size(), equalTo(12));
        assertThat(result.get(3).getBlocks().size(), equalTo(12));
        assertThat(result.get(4).getBlocks().size(), equalTo(11));
        assertThat(result.get(5).getBlocks().size(), equalTo(12));
        assertThat(result.get(6).getBlocks().size(), equalTo(12));
        assertThat(result.get(7).getBlocks().size(), equalTo(11));
        assertThat(result.get(8).getBlocks().size(), equalTo(12));
    }
}
