package com.fatec.mom.test.controller;

import com.fatec.mom.domain.document.Document;
import com.fatec.mom.domain.document.DocumentService;
import com.fatec.mom.test.integration.IntegrationTest;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@IntegrationTest
public class DocumentControllerTest extends AbstractControllerTest {

    @Test
    @Sql(value = "/com/fatec/mom/test/sql/insert-three-documents-and-twenty-five-blocks.sql",
        config = @SqlConfig(transactionManager = "dataSourceTransactionManager"))
    public void givenARequestToFindByNameAndPartNumberShouldReturn200AndTheListOfDocuments() throws Exception {
        var result = getMockMvc().perform(get("/document/find/all/by?document_name=ABC&part_number=1234"))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        var json = new JSONArray(response.getContentAsString());

        JSONAssert.assertEquals(
                jsonAsString("expected-doc-search-by-request-as-list.json"),
                getResultAsJson(result),
                true);

        assertThat(json.length(), equalTo(3));
    }
}
