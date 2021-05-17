package com.fatec.mom.infra.codelist.reader.codelistBuilder;

import com.fatec.mom.infra.codelist.reader.sheetcontent.ColumnDescriptor;
import com.fatec.mom.infra.codelist.reader.sheetcontent.RowDescriptor;
import com.fatec.mom.infra.codelist.reader.sheetcontent.SheetContent;
import com.fatec.mom.infra.codelist.reader.sheetcontent.ValueDescriptor;
import lombok.AccessLevel;
import lombok.Getter;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractCodelistBuilder {

    public AbstractCodelistBuilder(final String documentsPath, Map<CodelistMetadata, Object> metadataIndexes) {
        this.documentsPath = documentsPath;
        this.metadataIndexes.putAll(metadataIndexes);
    }

    @Getter(AccessLevel.PROTECTED)
    private final String documentsPath;

    private final Map<CodelistMetadata, Object> metadataIndexes = new EnumMap<>(CodelistMetadata.class);

    protected int getIndex(final CodelistMetadata metadata) {
        return (int) metadataIndexes.get(metadata);
    }

    protected String getValuesAsString(final SheetContent content,
                                       final RowDescriptor descriptor,
                                       final List<ColumnDescriptor> columns,
                                       final CodelistMetadata metadata) {
        final var value = content.getValue(new ValueDescriptor(descriptor, columns.get(getIndex(metadata))));
        return value.map(String::valueOf).orElse("");
    }

    protected Integer getValuesAsInteger(final SheetContent content,
                                       final RowDescriptor descriptor,
                                       final List<ColumnDescriptor> columns,
                                       final CodelistMetadata metadata) {
        final var value = content.getValue(new ValueDescriptor(descriptor, columns.get(getIndex(metadata))));
        return value.map(o -> Integer.parseInt(String.valueOf(o))).orElse(0);
    }

    protected Integer getValueAsInteger(final SheetContent content, final ValueDescriptor valueDescriptor) {
        final var value = content.getValue(valueDescriptor);
        return value.map(o -> (int) Math.round(Double.parseDouble(String.valueOf(o)))).orElse(0);
    }
}
