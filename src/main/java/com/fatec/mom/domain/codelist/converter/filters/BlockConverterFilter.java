package com.fatec.mom.domain.codelist.converter.filters;

import com.fatec.mom.domain.block.BlockDeserializer;
import com.fatec.mom.domain.document.Document;
import com.fatec.mom.domain.file.FileInfo;
import com.fatec.mom.domain.file.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * A classe <code>BlockConverterFilter</code> é responsável por converter as células do arquivo de codelist em objetos
 * do tipo <code>Block</code>.
 * Representa um handler dentro da corrente.
 *
 * @author Tobias Lino
 * @version v01 26/03/2021
 */
@Component
public class BlockConverterFilter extends AbstractConverterFilter {

    private static final int DEFAULT_COLUMN_INDEX = 6;
    private static final String NULLABLE_CELL = "NULL";

    @Autowired
    private Reader xlsReader;

    @Override
    public List<Document> doFilter(Document referenceDocument, FileInfo fileInfo, List<Document> documents) throws IOException {
        for (int row = fileInfo.getActualIndex(), order = 0; row < fileInfo.getTotalRows(); ++row, ++order) {
            var cells = xlsReader.getRow(fileInfo.getFileName(), row);

            var block = new BlockDeserializer().deserialize(cells, order);

            for (int i = DEFAULT_COLUMN_INDEX, traitIndex = 0; i < cells.size(); ++i, ++traitIndex) {
                if (!cells.get(i).equals(NULLABLE_CELL)) {
//                    documents.get(traitIndex).addBlock(block);
                }
            }
        }

        return doNextFilter(referenceDocument, fileInfo, documents);
    }
}
