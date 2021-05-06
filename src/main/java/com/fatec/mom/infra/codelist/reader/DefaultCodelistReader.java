package com.fatec.mom.infra.codelist.reader;

import com.fatec.mom.domain.document.Document;
import com.fatec.mom.infra.codelist.reader.config.CodelistConfigLocator;
import com.fatec.mom.infra.codelist.reader.config.CodelistConfigType;
import com.fatec.mom.infra.codelist.reader.contentsretrievers.CodelistContentRetriever;
import com.fatec.mom.infra.codelist.reader.sheetcontent.SheetContentsholder;
import com.fatec.mom.infra.exceptions.CodelistReaderException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
@AllArgsConstructor
public class DefaultCodelistReader implements CodelistReader {

    private final CodelistContentRetriever retriever;

    private final CodelistConfigLocator locator;

    @Override
    public List<Document> readCodelist(InputStream stream) {
        final SheetContentsholder contentsHolder = new SheetContentsholder();

        try (final Workbook workbook = WorkbookFactory.create(stream)) {
            retriever.addContents(locator.getConfig(CodelistConfigType.DEFAULT), workbook, contentsHolder);
        } catch (IOException e) {
            log.error("Error when importing codelist", e);
            throw new CodelistReaderException("Error when importing codelist", e);
        }

        return locator.getConfig(CodelistConfigType.DEFAULT).getInvoker().assembleDocument(contentsHolder);
    }
}
