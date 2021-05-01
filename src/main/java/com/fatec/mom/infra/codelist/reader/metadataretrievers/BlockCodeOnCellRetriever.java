package com.fatec.mom.infra.codelist.reader.metadataretrievers;

import com.fatec.mom.infra.codelist.reader.config.SingleCellData;
import com.fatec.mom.infra.codelist.reader.domain.CellBasedMetadataRetriever;
import com.fatec.mom.infra.codelist.reader.sheetcontent.SheetMetadata;
import com.fatec.mom.infra.codelist.reader.sheetcontent.SheetMetadataType;
import org.apache.poi.ss.usermodel.Sheet;

public class BlockCodeOnCellRetriever extends CellBasedMetadataRetriever {

    public BlockCodeOnCellRetriever(SingleCellData data) {
        super(data);
    }

    @Override
    public SheetMetadata retrieveMetadata(Sheet sheet) {
        final var code = Integer.parseInt(sheet.getRow(getData().getRow()).getCell(getData().getColumn()).getStringCellValue());
        return new SheetMetadata(SheetMetadataType.BLOCK_CODE, code);
    }
}
