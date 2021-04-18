package com.fatec.mom.infra.codelist.reader.codelistBuilder;

import com.fatec.mom.domain.document.Document;
import com.fatec.mom.infra.codelist.reader.sheetcontent.SheetContentsholder;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CodelistBuiderInvoker {

    private final CodelistBuilder builder;

    public List<Document> assembleDocument(final SheetContentsholder contentsholder) {
        contentsholder.getSheets().forEach(builder::transposeValues);
        return builder.build();
    }
}
