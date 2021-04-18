package com.fatec.mom.infra.codelist.reader.cellreaders.valueretrievers;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StringCellValueRetriever implements CellValueRetriever {

    @Override
    public Optional<Object> getValue(Cell cell) {
        if (cell.getStringCellValue().trim().length() > 0) {
            return Optional.of(cell.getStringCellValue());
        }
        return Optional.empty();
    }
}
