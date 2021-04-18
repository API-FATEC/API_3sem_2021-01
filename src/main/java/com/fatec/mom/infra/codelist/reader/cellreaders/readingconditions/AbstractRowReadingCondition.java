package com.fatec.mom.infra.codelist.reader.cellreaders.readingconditions;

import com.fatec.mom.infra.exceptions.CodelistReaderException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

public abstract class AbstractRowReadingCondition extends AbstractReadingCondition implements RowReadingCondition {

    private Row row;

    @Override
    public void setRow(Row row) {
        this.row = row;
    }

    @Override
    public boolean isRowEmpty() {
        Cell cell;

        if (row != null) {
            for (int cellNumber = 0; cellNumber < row.getLastCellNum(); ++cellNumber) {
                cell = row.getCell(cellNumber);
                if (cell != null && cell.getCellType() != CellType.BLANK) {
                    return false;
                }
            }
        }
        return true;
    }

    protected boolean isValueAssignedFromExpectedType() {
        if (!getValue().getClass().isAssignableFrom(String.class)) {
            throw new CodelistReaderException(null);
        } else {
            return true;
        }
    }
}
