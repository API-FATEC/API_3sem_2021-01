package com.fatec.mom.test.integration;

import com.fatec.mom.test.config.DatabaseTestConfig;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@SpringBootTest
@WebAppConfiguration
@ActiveProfiles("integration-tests")
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@ContextConfiguration(classes = {
        DatabaseTestConfig.class
})
public @interface IntegrationTest {
}
