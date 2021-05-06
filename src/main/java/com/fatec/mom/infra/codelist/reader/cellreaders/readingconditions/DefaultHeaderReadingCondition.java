package com.fatec.mom.infra.codelist.reader.cellreaders.readingconditions;

import org.springframework.stereotype.Component;

@Component
public class DefaultHeaderReadingCondition extends AbstractColumnReadingCondition {

    @Override
    public boolean shouldSkip() {
        return false;
    }

    @Override
    public boolean shouldStop() {
        return isHeaderEmpty();
    }
}
