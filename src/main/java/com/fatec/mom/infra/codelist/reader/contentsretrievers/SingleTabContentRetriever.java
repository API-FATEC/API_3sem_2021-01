package com.fatec.mom.infra.codelist.reader.contentsretrievers;

import com.fatec.mom.infra.codelist.reader.config.CodelistConfig;
import com.fatec.mom.infra.codelist.reader.config.SingleConfig;
import com.fatec.mom.infra.codelist.reader.sheetcontent.SheetContentsholder;
import com.fatec.mom.infra.codelist.reader.sheetreader.SheetReader;
import org.apache.poi.ss.usermodel.Workbook;

public class SingleTabContentRetriever extends AbstractCodelistContentRetriever {

    public SingleTabContentRetriever(SheetReader reader) {
        super(reader);
    }

    @Override
    public void addContents(final CodelistConfig config, Workbook workbook, SheetContentsholder sheetContentsholder) {
        final SingleConfig singleConfig = (SingleConfig) config;
        final int sheetIndex = singleConfig.getInitialIndex();
        sheetContentsholder.addSheetContents(getReader().readSheets(workbook.getSheetAt(sheetIndex), singleConfig.getConfig()));
    }
}
