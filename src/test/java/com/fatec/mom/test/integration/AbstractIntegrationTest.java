package com.fatec.mom.test.integration;

import net.minidev.json.JSONValue;
import org.springframework.test.web.servlet.MvcResult;

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/*
 * A classe <code>AbstractIntegrationTest</code> contém as características em comum para todas as classes
 * que realizam testes de integração.
 *
 * @author Tobias Lino
 * @version 0.1 15/03/2021
 */
public abstract class AbstractIntegrationTest {

    protected String jsonAsString(final String path) {
        InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream(String.format("/com/fatec/mom/test/jsons/%s", path)), StandardCharsets.UTF_8);
        return JSONValue.parse(reader).toString();
    }

    protected String getResultAsJson(MvcResult result) throws UnsupportedEncodingException {
        return result.getResponse().getContentAsString();
    }
}
