package com.fatec.mom.test.controller;

import com.fatec.mom.domain.user.User;
import com.fatec.mom.domain.user.UserService;
import com.fatec.mom.test.integration.IntegrationTest;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
class UserControllerTest extends AbstractControllerTest{

    @Autowired
    private UserService userService;

    @Test
    @Sql(value = "/com/fatec/mom/test/sql/insert-two-documents-and-five-users.sql",
        config = @SqlConfig(transactionManager = "dataSourceTransactionManager"))
    void shouldAddOneUser() throws Exception {
        User user = new User(null, "mario.nin@gmail.com", "EDIT_CODELIST", 1);
        getMockMvc().perform(
                post("/user/add", user)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(user))
        ).andExpect(status().isOk())
                .andReturn();

        System.out.println(userService.findAll());


        String result = new Gson().toJson(userService.findAll());
        System.out.println(result);

        JSONAssert.assertEquals(
                jsonAsString("expected-userTable-with-six-users.json"),
                result,
                true);
    }

}
