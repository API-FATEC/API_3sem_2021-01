package com.fatec.mom.test.domain.file;

import com.fatec.mom.domain.file.FileUploadService;
import com.fatec.mom.test.integration.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@IntegrationTest
class FileUploadServiceTest {

    @Autowired
    private FileUploadService fileUploadService;

    @Test
    void givenAMultipartFileItMustBeSavedInsideTheFolderDefinedByTheApplication() throws IOException {
        var file = new MockMultipartFile(
                "ABC-1234-00-00c50",
                "ABC-1234-00-00c50.pdf",
                MediaType.APPLICATION_PDF_VALUE,
                "ABC-1234-00-00c50".getBytes(StandardCharsets.UTF_8));
        fileUploadService.uploadFile(file);
        
        var path = new File("D:/Documents/facul/PI III/API/API_3sem_2021-01/upload-tests");
        var pathFiles = Objects.requireNonNull(path.list()).length > 1;

        assertThat(path.isDirectory(), equalTo(true));
        assertThat(pathFiles, equalTo(true));
    }
}
