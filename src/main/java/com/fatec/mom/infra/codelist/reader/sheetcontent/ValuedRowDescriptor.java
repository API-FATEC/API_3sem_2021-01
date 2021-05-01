package com.fatec.mom.infra.codelist.reader.sheetcontent;

public class ValuedRowDescriptor extends RowDescriptor {

    private final Object value;

    public ValuedRowDescriptor(int index, Object value) {
        super(index);
        this.value = value;
    }

    public boolean hasValue() {
        return true;
    }
}
