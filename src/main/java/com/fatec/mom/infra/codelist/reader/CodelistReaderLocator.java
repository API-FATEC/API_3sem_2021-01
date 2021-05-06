package com.fatec.mom.infra.codelist.reader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Component
public class CodelistReaderLocator {

    @Autowired
    private final Map<CodelistReaderType, CodelistReader> readers = new EnumMap<>(CodelistReaderType.class);

    public CodelistReader getReader(final CodelistReaderType type) {
        return readers.get(type);
    }
}
