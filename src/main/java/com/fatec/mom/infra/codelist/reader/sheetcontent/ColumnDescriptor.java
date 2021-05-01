package com.fatec.mom.infra.codelist.reader.sheetcontent;

import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

public class ColumnDescriptor {

    @Getter
    private final int index;

    private final List<Object> rows = new LinkedList<>();

    public ColumnDescriptor(int index, final List<Object> rows) {
        this.index = index;
        this.rows.addAll(rows);
    }
}
