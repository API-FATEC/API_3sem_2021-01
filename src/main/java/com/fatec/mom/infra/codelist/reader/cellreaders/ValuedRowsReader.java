package com.fatec.mom.infra.codelist.reader.cellreaders;

import com.fatec.mom.infra.codelist.reader.cellreaders.readingconditions.RowReadingCondition;
import com.fatec.mom.infra.codelist.reader.cellreaders.valueretrievers.CellValueRetrieverLocator;
import com.fatec.mom.infra.codelist.reader.sheetcontent.RowDescriptor;
import com.fatec.mom.infra.codelist.reader.sheetcontent.ValuedRowDescriptor;
import org.springframework.stereotype.Component;

public class ValuedRowsReader extends AbstractRowsReader {

    public ValuedRowsReader(CellValueRetrieverLocator locator, RowReadingCondition readingCondition) {
        super(locator, readingCondition);
    }

    @Override
    protected RowDescriptor createRowDescriptor(int rowNumber) {
        return new ValuedRowDescriptor(rowNumber, getReadingCondition().getValue());
    }
}
