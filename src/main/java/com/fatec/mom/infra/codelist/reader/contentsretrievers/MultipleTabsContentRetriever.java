package com.fatec.mom.infra.codelist.reader.contentsretrievers;

import com.fatec.mom.infra.codelist.reader.config.CodelistConfig;
import com.fatec.mom.infra.codelist.reader.config.SingleConfig;
import com.fatec.mom.infra.codelist.reader.sheetcontent.SheetContentsholder;
import com.fatec.mom.infra.codelist.reader.sheetreader.SheetReader;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class MultipleTabsContentRetriever extends AbstractCodelistContentRetriever {

    public MultipleTabsContentRetriever(SheetReader reader) {
        super(reader);
    }

    @Override
    public void addContents(CodelistConfig config, Workbook workbook, SheetContentsholder contentsholder) {
        final SingleConfig singleConfig = (SingleConfig) config;
        for (int i = singleConfig.getInitialIndex(); i < workbook.getNumberOfSheets(); ++i) {
            final Sheet sheet = workbook.getSheetAt(i);
            contentsholder.addSheetContents(getReader().readSheets(sheet, singleConfig.getConfig()));
        }
    }
}
