package com.fatec.mom.infra.codelist.reader.cellreaders.readingconditions;

import java.util.Optional;

public abstract class AbstractReadingCondition implements ReadingCondition {

    private Optional<Object> value;

    @Override
    public void setValue(Optional<Object> value) {
        this.value = value;
    }

    @Override
    public Object getValue() {
        return value.orElse(null);
    }

    @Override
    public boolean isValuePresent() {
        return value.isPresent();
    }
}
