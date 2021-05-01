package com.fatec.mom.infra.codelist.reader.contentsretrievers;

import com.fatec.mom.infra.codelist.reader.config.CodelistConfig;
import com.fatec.mom.infra.codelist.reader.sheetcontent.SheetContentsholder;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

@FunctionalInterface
public interface CodelistContentRetriever {

    void addContents(final CodelistConfig config, Workbook workbook, SheetContentsholder sheetContentsholder);
}
