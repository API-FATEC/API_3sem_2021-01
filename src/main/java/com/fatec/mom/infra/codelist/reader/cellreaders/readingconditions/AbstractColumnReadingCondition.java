package com.fatec.mom.infra.codelist.reader.cellreaders.readingconditions;

public abstract class AbstractColumnReadingCondition extends AbstractReadingCondition implements ColumnReadingCondition {

    @Override
    public boolean isHeaderEmpty() {
        return !isValuePresent();
    }
}
