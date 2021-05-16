package com.fatec.mom.test.controller;

import com.fatec.mom.test.integration.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
