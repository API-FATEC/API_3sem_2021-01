package com.fatec.mom.infra.codelist.reader.sheetreader;

import com.fatec.mom.infra.codelist.reader.cellreaders.CellReader;
import com.fatec.mom.infra.codelist.reader.cellreaders.HeadersReader;
import com.fatec.mom.infra.codelist.reader.cellreaders.RowsReader;
import com.fatec.mom.infra.codelist.reader.cellreaders.valueretrievers.CellValueRetrieverLocator;
import com.fatec.mom.infra.codelist.reader.config.CodelistSheetConfig;
import com.fatec.mom.infra.codelist.reader.sheetcontent.*;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class DefaultSheetReader extends CellReader implements SheetReader {

    private final HeadersReader headersReader;

    private final RowsReader rowsReader;

    public DefaultSheetReader(CellValueRetrieverLocator locator,
                              HeadersReader headersReader,
                              RowsReader rowsReader) {
        super(locator);
        this.headersReader = headersReader;
        this.rowsReader = rowsReader;
    }

    @Override
    public List<SheetContent> readSheets(Sheet sheet, CodelistSheetConfig config) {
        final var headers = headersReader.getHeaders(sheet, config.getColumns());
        final var rows = rowsReader.getRows(sheet, config.getRow());
        final var metadataRetrievers = config.getMetadataRetrievers();

        final var metadata = new LinkedList<SheetMetadata>();
        metadataRetrievers.stream().forEach(retriever -> metadata.add(retriever.retrieveMetadata(sheet)));

        final var content = new SheetContent(rows, headers, metadata);

        for (final RowDescriptor row : rows) {
            for (final ColumnDescriptor column : headers) {
                final ValueDescriptor value = new ValueDescriptor(row, column);
                final int rowNumber = row.getIndex();
                final int columnNumber = column.getIndex();

                content.addValue(value, getValue(sheet.getRow(rowNumber).getCell(columnNumber)));
            }
        }

        return Collections.singletonList(content);
    }
}
