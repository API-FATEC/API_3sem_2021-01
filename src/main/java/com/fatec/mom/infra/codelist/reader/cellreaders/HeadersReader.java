package com.fatec.mom.infra.codelist.reader.cellreaders;

import com.fatec.mom.infra.codelist.reader.cellreaders.readingconditions.ColumnReadingCondition;
import com.fatec.mom.infra.codelist.reader.cellreaders.valueretrievers.CellValueRetrieverLocator;
import com.fatec.mom.infra.codelist.reader.config.HeaderCellData;
import com.fatec.mom.infra.codelist.reader.sheetcontent.ColumnDescriptor;
import com.fatec.mom.infra.exceptions.CodelistImportException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class HeadersReader extends CellReader {

    private final ColumnReadingCondition readingCondition;

    public HeadersReader(CellValueRetrieverLocator locator, ColumnReadingCondition readingCondition) {
        super(locator);
        this.readingCondition = readingCondition;
    }

    public List<ColumnDescriptor> getHeaders(final Sheet sheet, final HeaderCellData data) {
        final List<ColumnDescriptor> columns = new LinkedList<>();
        int column = data.getColumn();

        synchronized (readingCondition) {
            while (true) {
                readingCondition.setValue((Optional) getColumnDescriptor(sheet, data, column));

                if (readingCondition.shouldStop()) {
                    break;
                } else if (!readingCondition.shouldSkip()) {
                    columns.add((ColumnDescriptor) readingCondition.getValue());
                }

                ++column;
            }
        }
        return columns;
    }

    private Optional<ColumnDescriptor> getColumnDescriptor(final Sheet sheet,
                                                           final HeaderCellData data,
                                                           final int column) {
        final Optional<List<Object>> values = getHeaderValues(sheet, data, column);
        return values.map(objects -> new ColumnDescriptor(column, objects));
    }

    private Optional<List<Object>> getHeaderValues(final Sheet sheet,
                                                   final HeaderCellData data,
                                                   final int column) {
        final var values = new LinkedList<Object>();

        for (int row : data.getRows()) {
            final Row sheetRow = sheet.getRow(row);

            if (sheetRow == null) {
                throw new CodelistImportException("Error to read Header, row not found: " + row + " in sheet: " + sheet.getSheetName());
            }

            var cell = sheetRow.getCell(column);
            if (cell == null || getValue(cell).isEmpty()) {
                values.add(Optional.empty());
            } else {
                values.add(getValue(cell).get());
            }
        }
        if (allValuesAreEmpty(values)) {
            return Optional.empty();
        }

        return Optional.of(values);

    }

    private boolean allValuesAreEmpty(final List<Object> values) {
        for (var obj : values) {
            if (!Optional.empty().equals(obj)) {
                return false;
            }
        }
        return true;
    }
}
