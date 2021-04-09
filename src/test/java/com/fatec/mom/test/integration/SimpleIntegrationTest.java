package com.fatec.mom.test.integration;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

@IntegrationTest
class SimpleIntegrationTest extends AbstractIntegrationTest {

    @Test
    void givenAJsonObjectItShouldReadTheFileAndValidate() throws JSONException {
        final String actualResult = simpleJsonObject();

        JSONAssert.assertEquals(jsonAsString("expected-example-entity.json"), actualResult, true);
    }

    private String simpleJsonObject() {
        SimpleObject simpleObject = new SimpleObject(1L, "Tobias Lino");
        return new Gson().toJson(simpleObject);
    }
}

@AllArgsConstructor
class SimpleObject {

    private Long id;
    private String name;
}