package com.fatec.mom.infra.generator;

import com.fatec.mom.domain.document.DocumentType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DocumentGeneratorRetriever {

    @Qualifier("fileGenerators")
    private Map<DocumentType, FileGenerator> generators;

    public FileGenerator getGenerator(final DocumentType documentType) {
        return generators.get(documentType);
    }
}
