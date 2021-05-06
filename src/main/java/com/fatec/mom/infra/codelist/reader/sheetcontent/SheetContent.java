package com.fatec.mom.infra.codelist.reader.sheetcontent;

import lombok.Getter;

import java.util.*;

public class SheetContent {

    @Getter
    private final List<RowDescriptor> rows = new LinkedList();
    @Getter
    private final List<ColumnDescriptor> columns = new LinkedList<>();
    private final List<SheetMetadata> metadata = new LinkedList<>();

    private final Map<ValueDescriptor, Optional<Object>> values = new LinkedHashMap<>();

    public SheetContent(final List<RowDescriptor> rows,
                        final List<ColumnDescriptor> columns,
                        final List<SheetMetadata> metadata) {
        this.rows.addAll(rows);
        this.columns.addAll(columns);
        this.metadata.addAll(metadata);
    }

    public void addValue(final ValueDescriptor descriptor, final Optional<Object> value) {
        values.put(descriptor, value);
    }

    public Optional<Object> getValue(final ValueDescriptor descriptor) {
        return values.get(descriptor).isEmpty() ? Optional.empty() : values.get(descriptor);
    }

    public SheetMetadata getMetadata(final SheetMetadataType type) {
        for (final var sheetMetadata : metadata) {
            if (sheetMetadata.hasType(type)) {
                return sheetMetadata;
            }
        }
        return null;
    }
}
