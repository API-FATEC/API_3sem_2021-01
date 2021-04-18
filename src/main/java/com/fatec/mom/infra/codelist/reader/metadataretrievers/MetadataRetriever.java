package com.fatec.mom.infra.codelist.reader.metadataretrievers;

import com.fatec.mom.infra.codelist.reader.sheetcontent.SheetMetadata;
import org.apache.poi.ss.usermodel.Sheet;

@FunctionalInterface
public interface MetadataRetriever {

    SheetMetadata retrieveMetadata(final Sheet sheet);
}
