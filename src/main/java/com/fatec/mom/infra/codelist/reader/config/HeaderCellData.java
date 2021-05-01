package com.fatec.mom.infra.codelist.reader.config;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public class HeaderCellData extends CellData {

    @Getter
    private final List<Integer> rows = new ArrayList<>();

    public HeaderCellData(final int column, final List<Integer> rows) {
        super(column);
        this.rows.addAll(rows);
    }
}
