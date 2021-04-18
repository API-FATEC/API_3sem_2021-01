package com.fatec.mom.infra.codelist.reader.cellreaders;

import com.fatec.mom.infra.codelist.reader.cellreaders.readingconditions.RowReadingCondition;
import com.fatec.mom.infra.codelist.reader.cellreaders.valueretrievers.CellValueRetrieverLocator;
import com.fatec.mom.infra.codelist.reader.config.SingleCellData;
import com.fatec.mom.infra.codelist.reader.sheetcontent.RowDescriptor;
import com.fatec.mom.infra.exceptions.CodelistImportException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Slf4j
public abstract class AbstractRowsReader extends CellReader implements RowsReader {

    @Getter(AccessLevel.PROTECTED)
    private final RowReadingCondition readingCondition;

    public AbstractRowsReader(CellValueRetrieverLocator locator, RowReadingCondition readingCondition) {
        super(locator);
        this.readingCondition = readingCondition;
    }

    public List<RowDescriptor> getRows(final Sheet sheet,
                                       final SingleCellData data) {
        final var rows = new LinkedList<RowDescriptor>();
        int column = data.getColumn();
        int rowNumber = data.getRow();
        Row row;

        synchronized (readingCondition) {
            while (true) {
                try {
                    log.debug("Reading Sheet: " + sheet.getSheetName() + ", row: " + rowNumber + ", column: " + column);
                    row = sheet.getRow(rowNumber);
                    readingCondition.setRow(row);
                    readingCondition.setValue(getRowDescriptorValue(row, column));

                    if (readingCondition.shouldStop()) {
                        break;
                    } else if (!readingCondition.shouldSkip()) {
                        rows.add(createRowDescriptor(rowNumber));
                    }
                    ++rowNumber;
                } catch (Exception e) {
                    ++rowNumber; ++column;
                    throw new CodelistImportException("Error in sheet: " + sheet.getSheetName() +", row: " + rowNumber + ", column: " + column + " cell type is not the same as expected", e);
                }
            }
        }
        return rows;
    }

    protected abstract RowDescriptor createRowDescriptor(final int rowNumber);

    private Optional<Object> getRowDescriptorValue(final Row row, final int column) {
        return row == null ? Optional.empty() : getValue(row.getCell(column));
    }
}
