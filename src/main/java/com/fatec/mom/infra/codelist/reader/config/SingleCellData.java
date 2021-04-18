package com.fatec.mom.infra.codelist.reader.config;

import lombok.Getter;

public class SingleCellData extends CellData {

    @Getter
    private final int row;

    public SingleCellData(int column, int row) {
        super(column);
        this.row = row;
    }
}
