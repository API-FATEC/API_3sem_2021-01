package com.fatec.mom.domain.document;

import com.fatec.mom.domain.utils.ConverterDeserializer;
import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
public class DocumentDeserializer implements ConverterDeserializer<Document> {

    private static final String CELL_LIMITER = " - ";

    private final String referenceDocumentName;
    private final Integer referenceDocumentPartNumber;
    private final Date referenceDocumentDate;

    @Override
    public List<Document> deserialize(List<String> rowCells) {
        var documents = new LinkedList<Document>();

        for (int i = 6; i < rowCells.size(); ++i) {
            var formattedTraitCell = formatTraitCell(rowCells.get(i));
            var document = Document.builder()
                    .name(referenceDocumentName)
                    .partNumber(referenceDocumentPartNumber)
//                    .trait(formattedTraitCell)
                    .createdDate(referenceDocumentDate)
                    .blocks(new HashSet<>())
                    .build();
            documents.add(document);
        }

        return documents;
    }

    public static Integer formatTraitCell(String cell) {
        var limiter = cell.indexOf(CELL_LIMITER);
        return Integer.parseInt(cell.substring(0, limiter));
    }
}
