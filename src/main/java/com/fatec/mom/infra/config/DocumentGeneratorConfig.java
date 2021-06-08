package com.fatec.mom.infra.config;

import com.fatec.mom.domain.document.DocumentType;
import com.fatec.mom.infra.generator.FileGenerator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EnumMap;
import java.util.Map;

@Configuration
public class DocumentGeneratorConfig {

    @Bean
    public Map<DocumentType, FileGenerator> fileGenerators(@Qualifier("deltaGenerator") final FileGenerator deltaGenerator,
                                                           @Qualifier("fullDocumentGenerator") final FileGenerator fullGenerator,
                                                           @Qualifier("LEPGenerator") final FileGenerator lepGenerator) {
        final Map<DocumentType, FileGenerator> generators = new EnumMap<>(DocumentType.class);
        generators.put(DocumentType.DELTA, deltaGenerator);
        generators.put(DocumentType.FULL, fullGenerator);
        generators.put(DocumentType.LEP, lepGenerator);
        return generators;
    }
}
