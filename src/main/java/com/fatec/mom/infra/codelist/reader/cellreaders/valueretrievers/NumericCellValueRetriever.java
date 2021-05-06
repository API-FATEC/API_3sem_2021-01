package com.fatec.mom.infra.codelist.reader.cellreaders.valueretrievers;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class NumericCellValueRetriever implements CellValueRetriever {

    @Override
    public Optional<Object> getValue(Cell cell) {
        return Optional.of(cell.getNumericCellValue());
    }
}
