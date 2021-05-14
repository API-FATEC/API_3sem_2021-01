package com.fatec.mom.test.domain.file;

import com.fatec.mom.domain.block.Block;
import com.fatec.mom.infra.file.extractor.FileExtractor;
import com.fatec.mom.test.integration.IntegrationTest;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

@IntegrationTest
public class FileExtractorTest {

    @Autowired
    FileExtractor fileExtractor;

    @Test
    @DisplayName("Try to extract pages from pdf file")
    @Sql(value = "/com/fatec/mom/test/sql/all-tables.sql")
    void tryToExtractPagesFromPdfFile() {
        final var stream = getClass().getResourceAsStream("/com/fatec/mom/test/documents/ABC-1234-03-01-03c14.pdf");
        final var pages = fileExtractor.extractPages(new Block(), stream);

        System.out.println(new Gson().toJson(pages));

    }
}
