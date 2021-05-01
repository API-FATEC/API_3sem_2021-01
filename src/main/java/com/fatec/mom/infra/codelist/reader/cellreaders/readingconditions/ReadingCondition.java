package com.fatec.mom.infra.codelist.reader.cellreaders.readingconditions;

import org.springframework.stereotype.Component;

import java.util.Optional;

public interface ReadingCondition {

    void setValue(final Optional<Object> value);
    Object getValue();
    boolean shouldSkip();
    boolean shouldStop();
    boolean isValuePresent();
}
