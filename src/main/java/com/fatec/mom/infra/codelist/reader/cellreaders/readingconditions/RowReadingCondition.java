package com.fatec.mom.infra.codelist.reader.cellreaders.readingconditions;

import org.apache.poi.ss.usermodel.Row;

public interface RowReadingCondition extends ReadingCondition {

    void setRow(final Row row);
    boolean isRowEmpty();
}
