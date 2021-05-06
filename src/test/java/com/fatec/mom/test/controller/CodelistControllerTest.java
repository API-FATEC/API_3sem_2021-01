package com.fatec.mom.test.controller;

import com.fatec.mom.domain.codelist.CodelistConverterService;
//import com.fatec.mom.domain.codelist.CodelistService;
import com.fatec.mom.domain.file.FileInfoService;
import com.fatec.mom.domain.file.FileUploadService;
import com.fatec.mom.test.integration.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@IntegrationTest
public class CodelistControllerTest extends AbstractControllerTest {

    @Test
    @Sql(value = "/com/fatec/mom/test/sql/insert-three-documents-and-twenty-five-blocks.sql",
            config = @SqlConfig(transactionManager = "dataSourceTransactionManager"))
    void shouldReturnOneCodelist() throws Exception {
        var result = getMockMvc().perform(get("/codelist/find/by?document_name=ABC&part_number=1234"))
                .andExpect(status().isOk())
                .andReturn();
        var json = getResultAsJson(result);
        System.out.println(json);
    }
}
