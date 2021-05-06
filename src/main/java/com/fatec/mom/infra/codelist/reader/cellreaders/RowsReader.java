package com.fatec.mom.infra.codelist.reader.cellreaders;

import com.fatec.mom.infra.codelist.reader.config.SingleCellData;
import com.fatec.mom.infra.codelist.reader.sheetcontent.RowDescriptor;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Component;

import java.util.List;

public interface RowsReader {

    List<RowDescriptor> getRows(final Sheet sheet, final SingleCellData data);
}
