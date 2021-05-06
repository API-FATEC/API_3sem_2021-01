package com.fatec.mom.infra.codelist.reader.sheetcontent;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SheetMetadata {

    private final SheetMetadataType type;
    private final Object value;

    public boolean hasType(final SheetMetadataType type) {
        return this.type == type;
    }
}
