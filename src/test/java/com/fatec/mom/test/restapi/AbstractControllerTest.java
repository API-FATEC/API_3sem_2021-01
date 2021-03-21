package com.fatec.mom.test.restapi;

import lombok.AccessLevel;
import lombok.Getter;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

public abstract class AbstractControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Getter(AccessLevel.PROTECTED)
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws Exception {
        mockMvc = webAppContextSetup(context)
                .apply(springSecurity()).build();
    }

}
