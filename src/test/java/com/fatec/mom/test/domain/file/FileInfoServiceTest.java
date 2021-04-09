package com.fatec.mom.test.domain.file;

import com.fatec.mom.domain.file.FileInfoService;
import com.fatec.mom.test.integration.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@IntegrationTest
class FileInfoServiceTest {

    @Autowired
    private FileInfoService fileInfoService;

    @Test
    void buildFileIndoByCodelistFile() throws IOException {
        var fileInfo = fileInfoService.build("Codelist.xlsx");

        assertThat(fileInfo.getActualIndex(), equalTo(1));
        assertThat(fileInfo.getTotalRows(), equalTo(28));
        assertThat(fileInfo.getFileName(), equalTo("Codelist.xlsx"));
    }
}
