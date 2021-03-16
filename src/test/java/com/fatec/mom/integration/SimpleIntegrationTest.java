package com.fatec.mom.integration;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

@IntegrationTest
public class SimpleIntegrationTest extends AbstractIntegrationTest {

    @Test
    public void givenAJsonObjectItShouldReadTheFileAndValidate() throws JSONException {
        final String actualResult = simpleJsonObject();

        JSONAssert.assertEquals(jsonAsString("expected-example-entity.json"), actualResult, true);
    }

    private String simpleJsonObject() {
        SimpleObject simpleObject = new SimpleObject(1L, "Tobias Lino");
        return new Gson().toJson(simpleObject);
    }
}

@Data @AllArgsConstructor
class SimpleObject {

    private Long id;
    private String name;
}