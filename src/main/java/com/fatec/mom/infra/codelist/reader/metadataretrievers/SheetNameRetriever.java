package com.fatec.mom.infra.codelist.reader.metadataretrievers;

import com.fatec.mom.infra.codelist.reader.sheetcontent.SheetMetadata;
import com.fatec.mom.infra.codelist.reader.sheetcontent.SheetMetadataType;
import org.apache.poi.ss.usermodel.Sheet;

public class SheetNameRetriever implements MetadataRetriever {

    @Override
    public SheetMetadata retrieveMetadata(Sheet sheet) {
        return new SheetMetadata(SheetMetadataType.SHEET_NAME, sheet.getSheetName());
    }
}
