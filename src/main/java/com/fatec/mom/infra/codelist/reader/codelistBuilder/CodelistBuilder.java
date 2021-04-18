package com.fatec.mom.infra.codelist.reader.codelistBuilder;

import com.fatec.mom.domain.block.Block;
import com.fatec.mom.domain.document.Document;
import com.fatec.mom.infra.codelist.reader.sheetcontent.*;
import lombok.Getter;

import java.util.*;

public class CodelistBuilder extends AbstractCodelistBuilder {

    public CodelistBuilder(Map<CodelistMetadata, Object> metadataIndexes) {
        super(metadataIndexes);
    }

    @Getter
    private List<Document> documents = new LinkedList<>();

    public void transposeValues(final SheetContent content) {
        final List<ColumnDescriptor> columns = content.getColumns();
        final List<Integer> traits = (List<Integer>) content.getMetadata(SheetMetadataType.DOCUMENT_TRAITS).getValue();
        final String documentName = (String) content.getMetadata(SheetMetadataType.DOCUMENT_NAME).getValue();
        final Integer documentPartNumber = (Integer) content.getMetadata(SheetMetadataType.DOCUMENT_PART_NUMBER).getValue();

        documents.addAll(getDocuments(traits, documentName, documentPartNumber));

        int order = 0;
        for (final RowDescriptor descriptor : content.getRows()) {
            final Block block = getBlock(content, descriptor, columns, ++order);

            for (int i = getIndex(CodelistMetadata.TRAIT_VALUES_BEGIN), traitIndex = 0; i < columns.size(); ++i, ++traitIndex) {
                final ColumnDescriptor column = columns.get(i);
                final ValueDescriptor valueDescriptor = new ValueDescriptor(descriptor, column);
                final int value = getValueAsInteger(content, valueDescriptor);
                if (value == 1) {
                    documents.get(traitIndex).addBlock(block);
                }
            }
        }
    }

    public List<Document> build() {
        return this.documents;
    }

    private List<Document> getDocuments(List<Integer> traits, String documentName, Integer documentPartNumber) {
        final var documents = new LinkedList<Document>();
        if (traits.size() > 0) {
            for (final var trait : traits) {
                final Document document = Document.builder()
                        .name(documentName)
                        .partNumber(documentPartNumber)
                        .trait(trait)
                        .blocks(new HashSet<>())
                        .createdDate(new Date())
                        .build();
                documents.add(document);
            }
        }
        return documents;
    }

    private Block getBlock(final SheetContent content,
                           final RowDescriptor descriptor,
                           final List<ColumnDescriptor> columns,
                           int order) {
        final String section = getValuesAsString(content, descriptor, columns, CodelistMetadata.SECTION_COLUMN);
        final String subSection = getValuesAsString(content, descriptor, columns, CodelistMetadata.SUB_SECTION_COLUMN);
        final String name = getValuesAsString(content, descriptor, columns, CodelistMetadata.NAME_COLUMN);
        final Integer number = getValuesAsInteger(content, descriptor, columns, CodelistMetadata.NUMBER_COLUMN);
        final Integer code = getValuesAsInteger(content, descriptor, columns, CodelistMetadata.CODE_COLUMN);

        return Block.builder()
                .section(section)
                .subSection(subSection)
                .name(name)
                .number(number)
                .code(code)
                .order(order)
                .build();
    }
}
