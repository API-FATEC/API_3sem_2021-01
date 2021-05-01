package com.fatec.mom.infra.codelist.reader.contentsretrievers;

import com.fatec.mom.infra.codelist.reader.sheetreader.SheetReader;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractCodelistContentRetriever implements CodelistContentRetriever {

    @Autowired
    @Getter
    private final SheetReader reader;
}
