package com.fatec.mom.infra.codelist.reader.metadataretrievers;

import com.fatec.mom.infra.codelist.reader.config.SingleCellData;
import com.fatec.mom.infra.codelist.reader.domain.CellBasedMetadataRetriever;
import com.fatec.mom.infra.codelist.reader.sheetcontent.SheetMetadata;
import com.fatec.mom.infra.codelist.reader.sheetcontent.SheetMetadataType;
import org.apache.poi.ss.usermodel.Sheet;

public class BlockRemarksOnCellRetriever extends CellBasedMetadataRetriever {

    public BlockRemarksOnCellRetriever(SingleCellData data) {
        super(data);
    }

    @Override
    public SheetMetadata retrieveMetadata(Sheet sheet) {
        final var remarks = sheet.getRow(getData().getRow()).getCell(getData().getColumn()).getNumericCellValue();
        return new SheetMetadata(SheetMetadataType.BLOCK_REMARKS, remarks);
    }
}
