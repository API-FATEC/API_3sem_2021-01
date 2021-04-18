package com.fatec.mom.infra.codelist.reader.cellreaders.readingconditions;

import org.springframework.stereotype.Component;

@Component
public class DefaultRowReadingCondition extends AbstractRowReadingCondition {

    public DefaultRowReadingCondition() {
        super();
    }

    @Override
    public boolean shouldSkip() {
        return false;
    }

    @Override
    public boolean shouldStop() {
        return !isValuePresent() || !isValueAssignedFromExpectedType();
    }
}
