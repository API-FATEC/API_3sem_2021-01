package com.fatec.mom.infra.codelist.reader.cellreaders.valueretrievers;

import com.fatec.mom.infra.codelist.reader.domain.CellValueType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Component
public class CellValueRetrieverLocator {

    @Autowired
    public CellValueRetrieverLocator(final Map<CellValueType, CellValueRetriever> retrievers) {
        this.retrievers.putAll(retrievers);
    }

    private final Map<CellValueType, CellValueRetriever> retrievers = new EnumMap<>(CellValueType.class);

    public CellValueRetriever getRetriever(final CellValueType type) {
        return retrievers.get(type);
    }
}
