package com.fatec.mom.infra.codelist.reader.cellreaders;

import com.fatec.mom.infra.codelist.reader.cellreaders.readingconditions.RowReadingCondition;
import com.fatec.mom.infra.codelist.reader.cellreaders.valueretrievers.CellValueRetrieverLocator;
import com.fatec.mom.infra.codelist.reader.sheetcontent.RowDescriptor;

public class IndexedRowsReader extends AbstractRowsReader {

    public IndexedRowsReader(CellValueRetrieverLocator locator, RowReadingCondition readingCondition) {
        super(locator, readingCondition);
    }

    @Override
    protected RowDescriptor createRowDescriptor(int rowNumber) {
        return new RowDescriptor(rowNumber);
    }
}
