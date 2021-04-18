package com.fatec.mom.infra.codelist.reader.cellreaders;

import com.fatec.mom.infra.codelist.reader.cellreaders.valueretrievers.CellValueRetrieverLocator;
import com.fatec.mom.infra.codelist.reader.domain.CellValueType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class CellReader {

    @Qualifier("cellValueRetrieverLocator")
    private final CellValueRetrieverLocator locator;

    protected Optional<Object> getValue(final Cell cell) {
        return locator.getRetriever(getType(cell)).getValue(cell);
    }

    private CellValueType getType(final Cell cell) {
        if (cell == null) {
            return CellValueType.EMPTY;
        } else if (cell.getCellType() == null) {
            return CellValueType.EMPTY;
        }
        return CellValueType.valueOf(cell.getCellType().name());
    }
}
