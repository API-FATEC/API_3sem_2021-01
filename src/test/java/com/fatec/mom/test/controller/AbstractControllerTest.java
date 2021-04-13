package com.fatec.mom.test.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fatec.mom.test.integration.AbstractIntegrationTest;
import lombok.AccessLevel;
import lombok.Getter;
import org.json.JSONArray;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;

class AbstractControllerTest extends AbstractIntegrationTest {

    @Autowired
    private WebApplicationContext context;

    @Getter(AccessLevel.PROTECTED)
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    protected JSONArray getJsonArray(MvcResult result) throws UnsupportedEncodingException, JSONException {
        MockHttpServletResponse response = result.getResponse();
        return new JSONArray(response.getContentAsString());
    }

    protected String asJsonString(final Object object) throws JsonProcessingException {
        final var mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
