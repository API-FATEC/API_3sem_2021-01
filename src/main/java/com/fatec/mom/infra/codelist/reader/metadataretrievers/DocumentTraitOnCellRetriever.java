package com.fatec.mom.infra.codelist.reader.metadataretrievers;

import com.fatec.mom.infra.codelist.reader.config.SingleCellData;
import com.fatec.mom.infra.codelist.reader.domain.CellBasedMetadataRetriever;
import com.fatec.mom.infra.codelist.reader.domain.SheetDocumentMetadataFactory;
import com.fatec.mom.infra.codelist.reader.sheetcontent.SheetMetadata;
import com.fatec.mom.infra.codelist.reader.sheetcontent.SheetMetadataType;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.LinkedList;
import java.util.List;

public class DocumentTraitOnCellRetriever extends CellBasedMetadataRetriever {

    public DocumentTraitOnCellRetriever(SingleCellData data) {
        super(data);
    }

    @Override
    public SheetMetadata retrieveMetadata(Sheet sheet) {
        final List<Integer> traits = new LinkedList<>();
        for (int i = getData().getColumn(); i < sheet.getRow(getData().getRow()).getPhysicalNumberOfCells(); ++i) {
            final String traitName = sheet.getRow(getData().getRow()).getCell(i).getStringCellValue();
            final Integer trait = SheetDocumentMetadataFactory.getTraitFrom(traitName);
            traits.add(trait);
        }
        return new SheetMetadata(SheetMetadataType.DOCUMENT_TRAITS, traits);
    }
}
