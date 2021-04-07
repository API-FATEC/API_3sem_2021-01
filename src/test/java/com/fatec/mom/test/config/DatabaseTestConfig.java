package com.fatec.mom.test.config;

import com.fatec.mom.infra.config.DatabaseCommonsConfig;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@TestConfiguration
@Import({DatabaseCommonsConfig.class, JpaTestConfig.class})
public class DatabaseTestConfig {

    @Bean
    public JdbcTemplate template(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
