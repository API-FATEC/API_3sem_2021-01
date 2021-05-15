package com.fatec.mom.test.domain.file;

import com.fatec.mom.domain.file.FileUploadService;
import com.fatec.mom.domain.file.UploadedFileRepository;
import com.fatec.mom.test.integration.IntegrationTest;
import org.hibernate.annotations.Source;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@IntegrationTest
class FileUploadServiceTest {

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    UploadedFileRepository uploadedFileRepository;

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

    @ParameterizedTest
    @DisplayName("Given a multipartFile It must be saved to database")
    @Sql(value = "/com/fatec/mom/test/sql/all-tables.sql",
            config = @SqlConfig(transactionManager = "dataSourceTransactionManager"))
    @ValueSource(strings = {
            "ABC-1234-03-01-03c14.pdf",
            "ABC-1234-55-REV6-FULL.pdf",
            "ABC-1234-55-REV6-DELTA.pdf",
            "ABC-1234-55-REV7-FULL.pdf",
            "ABC-1234-60-REV6-FULL.pdf",
            "ABC-1234-60-REV7-FULL.pdf",
            "ABC-1234-60-REV7-DELTA.pdf"
    })
    void givenAMultipartFileItMustBeSavedToDatabase(final String fileName) throws IOException {
        var inputStream = getClass().getResourceAsStream(String.format("/com/fatec/mom/test/documents/%s", fileName));
        var file = new MockMultipartFile(
                fileName,
                fileName,
                MediaType.APPLICATION_PDF_VALUE,
                inputStream);
        var uploadedFile = fileUploadService.uploadFile(file);

        var savedFile = uploadedFileRepository.findById(1L).orElseThrow();

        System.out.println(uploadedFile.getAbsolutePath());
        System.out.println(uploadedFile.getAbsoluteFile());
        System.out.println(uploadedFile.getName());

        System.out.println(savedFile);
    }
}
