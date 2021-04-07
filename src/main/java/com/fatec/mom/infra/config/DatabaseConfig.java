package com.fatec.mom.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@Profile("!integration-tests")
@Import({DatabaseCommonsConfig.class, DefaultJpaConfig.class})
public class DatabaseConfig {

    @Bean
    public JdbcTemplate template(final DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
