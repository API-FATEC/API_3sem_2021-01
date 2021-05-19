package com.fatec.mom.infra.codelist.reader.codelistBuilder;

import com.fatec.mom.domain.block.Block;
import com.fatec.mom.domain.block.BlockStatus;
import com.fatec.mom.domain.document.Document;
import com.fatec.mom.domain.trait.Trait;
import com.fatec.mom.infra.codelist.reader.sheetcontent.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.*;

public class CodelistBuilder extends AbstractCodelistBuilder {

    public CodelistBuilder(String documentsPath, Map<CodelistMetadata, Object> metadataIndexes) {
        super(documentsPath, metadataIndexes);
    }

    public Document transposeValues(final SheetContent content) {
        final List<ColumnDescriptor> columns = content.getColumns();
        final List<Integer> traits = (List<Integer>) content.getMetadata(SheetMetadataType.DOCUMENT_TRAITS).getValue();
        final String documentName = (String) content.getMetadata(SheetMetadataType.DOCUMENT_NAME).getValue();
        final Integer documentPartNumber = (Integer) content.getMetadata(SheetMetadataType.DOCUMENT_PART_NUMBER).getValue();

        Document document = getDocument(traits, documentName, documentPartNumber);

        int order = 0;
        for (final RowDescriptor descriptor : content.getRows()) {
            final Block block = getBlock(content, descriptor, columns, ++order, document);

            for (int i = getIndex(CodelistMetadata.TRAIT_VALUES_BEGIN), traitIndex = 0; i < columns.size(); ++i, ++traitIndex) {
                final ColumnDescriptor column = columns.get(i);
                final ValueDescriptor valueDescriptor = new ValueDescriptor(descriptor, column);
                final int value = getValueAsInteger(content, valueDescriptor);
                if (value == 1) {
                    final int finalTraitIndex = traitIndex;
                    block.addTrait(document.getTraits().stream()
                            .filter(trait -> trait.getNumber().equals(traits.get(finalTraitIndex)))
                            .findFirst().orElseThrow());
                }
            }

            document.addBlock(block);
        }

        return document;
    }

    private Document getDocument(List<Integer> traitsToAdd, String documentName, Integer documentPartNumber) {
        final var traits = new HashSet<Trait>();
        if (traitsToAdd.size() > 0) {
            for (final var trait : traitsToAdd) {
                traits.add(Trait.builder().number(trait).build());
            }
        }
        return Document.builder()
                .name(documentName)
                .partNumber(documentPartNumber)
                .createdDate(new Date())
                .traits(traits)
                .revisions(new HashSet<>())
                .tags(new HashSet<>())
                .blocks(new HashSet<>())
                .build();
    }

    private Block getBlock(final SheetContent content,
                           final RowDescriptor descriptor,
                           final List<ColumnDescriptor> columns,
                           int order,
                           final Document document) {
        final String section = getValuesAsString(content, descriptor, columns, CodelistMetadata.SECTION_COLUMN);
        final String subSection = getValuesAsString(content, descriptor, columns, CodelistMetadata.SUB_SECTION_COLUMN);
        final String name = getValuesAsString(content, descriptor, columns, CodelistMetadata.NAME_COLUMN);
        final Integer number = getValuesAsInteger(content, descriptor, columns, CodelistMetadata.NUMBER_COLUMN);
        final Integer code = getValuesAsInteger(content, descriptor, columns, CodelistMetadata.CODE_COLUMN);

        Block block = Block.builder()
                .section(section)
                .subSection(subSection)
                .name(name)
                .number(number)
                .code(code)
                .order(order)
                .status(BlockStatus.CLOSED)
                .traits(new HashSet<>())
                .tags(new HashSet<>())
                .links(new HashSet<>())
                .build();
        block.setBasePath(String.format("%s/Master/%s/%s", getDocumentsPath(), document.getDocument(), block.getBlockName(document)));

        return block;
    }
}
