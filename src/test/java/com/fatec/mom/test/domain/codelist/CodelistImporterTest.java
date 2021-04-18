package com.fatec.mom.test.domain.codelist;

import com.fatec.mom.infra.codelist.reader.CodelistReader;
import com.fatec.mom.infra.codelist.reader.CodelistReaderLocator;
import com.fatec.mom.infra.codelist.reader.CodelistReaderType;
import com.fatec.mom.infra.codelist.reader.config.CodelistConfigType;
import com.fatec.mom.test.integration.IntegrationTest;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import javax.annotation.Resource;

@IntegrationTest
public class CodelistImporterTest {

    @Resource
    private CodelistReaderLocator locator;

    @Test
    @Sql(value = "/com/fatec/mom/test/sql/docs-blocks-tables.sql")
    void runSingleTabImport() {
        final var stream = getClass().getResourceAsStream("/com/fatec/mom/test/documents/Codelist.xlsx");
        final var reader = locator.getReader(CodelistReaderType.SINGLE_TAB);
        final var documents = reader.readCodelist(CodelistConfigType.DEFAULT, stream);

        var json = new Gson().toJson(documents);
        System.out.println(json);
    }

    @Test
    @Sql(value = "/com/fatec/mom/test/sql/docs-blocks-tables.sql")
    void runMultipleTabImport() {
        final var stream = getClass().getResourceAsStream("/com/fatec/mom/test/documents/MultipleTabCodelist.xlsx");
        final var reader = locator.getReader(CodelistReaderType.MULTIPLE_TAB);
        final var documents = reader.readCodelist(CodelistConfigType.DEFAULT, stream);

        var json = new Gson().toJson(documents);
        System.out.println(json);
    }
}
