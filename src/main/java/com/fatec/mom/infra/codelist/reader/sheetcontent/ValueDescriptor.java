package com.fatec.mom.infra.codelist.reader.sheetcontent;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
public class ValueDescriptor {

    private final RowDescriptor row;
    private final ColumnDescriptor column;
}
