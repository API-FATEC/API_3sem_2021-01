package com.fatec.mom.infra.codelist.reader.cellreaders.valueretrievers;

import org.apache.poi.ss.usermodel.Cell;

import java.util.Optional;

@FunctionalInterface
public interface CellValueRetriever {

    Optional<Object> getValue(final Cell cell);
}
