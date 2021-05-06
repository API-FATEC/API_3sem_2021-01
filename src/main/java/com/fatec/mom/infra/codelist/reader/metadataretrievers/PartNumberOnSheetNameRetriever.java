package com.fatec.mom.infra.codelist.reader.metadataretrievers;

import com.fatec.mom.infra.codelist.reader.domain.SheetDocumentMetadataFactory;
import com.fatec.mom.infra.codelist.reader.sheetcontent.SheetMetadata;
import com.fatec.mom.infra.codelist.reader.sheetcontent.SheetMetadataType;
import org.apache.poi.ss.usermodel.Sheet;

public class PartNumberOnSheetNameRetriever implements MetadataRetriever {

    @Override
    public SheetMetadata retrieveMetadata(Sheet sheet) {
        final var sheetName = sheet.getSheetName();
        return new SheetMetadata(SheetMetadataType.DOCUMENT_PART_NUMBER, SheetDocumentMetadataFactory.getPartNumberFrom(sheetName));
    }
}
