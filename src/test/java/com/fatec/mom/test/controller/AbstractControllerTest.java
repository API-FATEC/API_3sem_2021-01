package com.fatec.mom.test.controller;

import com.fatec.mom.test.integration.AbstractIntegrationTest;
import lombok.AccessLevel;
import lombok.Getter;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class AbstractControllerTest extends AbstractIntegrationTest {

    @Autowired
    private WebApplicationContext context;

    @Getter(AccessLevel.PROTECTED)
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
}
