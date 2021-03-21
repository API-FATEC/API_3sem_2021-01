package com.fatec.mom.test.restapi.authentication;

import com.fatec.mom.application.SimpleSwagger;
import com.fatec.mom.test.integration.IntegrationTest;
import com.fatec.mom.test.restapi.AbstractControllerTest;
import com.fatec.mom.test.security.mocked.WithUser;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * A classe <code>SimpleAuthenticatedControllerTest</code> testa se o login com o usuário padrão está sendo relizado corretamente
 */
@IntegrationTest
public class SimpleAuthenticatedControllerTest extends AbstractControllerTest {

    @Test
    @Sql("/com/fatec/mom/test/sql/users/two-users-insert.sql")
    @WithUser
    public void attemptAuthenticate() throws Exception {
        getMockMvc().perform(get("/hello/world"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello World")));
    }

    @Test
    @Sql("/com/fatec/mom/test/sql/users/two-users-insert.sql")
    @WithUser(username = "tslino", roles = "ADMIN")
    public void givenARequestInAAdminOnlyMethodWithAnAdminUserShouldSuccess() throws Exception {
        getMockMvc().perform(get("/hello/world/admin"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello Admin")));
    }

    @Test
    @Sql("/com/fatec/mom/test/sql/users/two-users-insert.sql")
    @WithUser
    public void givenARequestInAAdminOnlyMethodWithAnNotAdminUserShouldFailWith403() throws Exception {
        getMockMvc().perform(get("/hello/world/admin"))
                .andExpect(status().is(403));
    }
}
