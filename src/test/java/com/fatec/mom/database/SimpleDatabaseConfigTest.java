package com.fatec.mom.database;

import com.fatec.mom.integration.AbstractIntegrationTest;
import com.fatec.mom.integration.IntegrationTest;
import com.google.gson.Gson;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.jdbc.Sql;

import javax.persistence.*;
import java.util.List;

@IntegrationTest
public class SimpleDatabaseConfigTest extends AbstractIntegrationTest {

    @Autowired
    private ExampleEntityRepository exampleEntityRepository;

    @Test
    @Sql(value = "/com/fatec/mom/sql/example-entity.sql")
    public void givenATestDatabaseItMustFetchAllTheDataEntered() throws JSONException {
        List<ExampleEntity> exampleEntities = exampleEntityRepository.findAll();
        String result = new Gson().toJson(exampleEntities);

        JSONAssert.assertEquals(jsonAsString("expected-example-entity-list.json"), result, true);
    }
}

@Entity
@Table(name = "EXAMPLE_ENTITY")
class ExampleEntity {

    @Id
    @Column(name = "COD_ENTITY")
    private Long id;

    @Column(name = "NOME")
    private String nome;
}

@Repository
interface ExampleEntityRepository extends JpaRepository<ExampleEntity, Long> {}
