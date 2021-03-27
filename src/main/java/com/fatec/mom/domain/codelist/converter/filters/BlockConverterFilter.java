package com.fatec.mom.domain.codelist.converter.filters;

import com.fatec.mom.domain.block.BlockDeserializer;
import com.fatec.mom.domain.document.Document;
import com.fatec.mom.domain.file.FileInfo;
import com.fatec.mom.domain.file.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BlockConverterFilter extends AbstractConverterFilter {

    private final static int DEFAULT_COLUMN_INDEX = 6;
    private final static String NULLABLE_CELL = "NULL";

    @Autowired
    private Reader xlsReader;

    @Override
    public void doFilter(Document referenceDocument, FileInfo fileInfo, List<Document> documents) {
        for (int row = fileInfo.getActualIndex(); row < fileInfo.getTotalRows(); ++row) {
            var cells = xlsReader.getRow(fileInfo.getFileName(), row);

            var block = new BlockDeserializer().deserialize(cells);

            for (int i = DEFAULT_COLUMN_INDEX, traitIndex = 0; i < cells.size(); ++i, ++traitIndex) {
                if (!cells.get(i).equals(NULLABLE_CELL)) {
                    documents.get(traitIndex).addBlock(block);
                }
            }
        }

        doNextFilter(referenceDocument, fileInfo, documents);
    }
}
