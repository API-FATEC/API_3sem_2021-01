package com.fatec.mom.infra.codelist.reader.sheetreader;

import com.fatec.mom.infra.codelist.reader.config.CodelistSheetConfig;
import com.fatec.mom.infra.codelist.reader.sheetcontent.SheetContent;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

@FunctionalInterface
public interface SheetReader {

    List<SheetContent> readSheets(final Sheet sheet, final CodelistSheetConfig config);
}
