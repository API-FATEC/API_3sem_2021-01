package com.fatec.mom.infra.config;

import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GitConfig {

    @Bean
    public FileRepositoryBuilder builder() {
        return new FileRepositoryBuilder();
    }
}
