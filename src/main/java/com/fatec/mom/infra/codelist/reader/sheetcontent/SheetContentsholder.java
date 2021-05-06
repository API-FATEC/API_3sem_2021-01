package com.fatec.mom.infra.codelist.reader.sheetcontent;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SheetContentsholder {

    private final List<SheetContent> contents = new LinkedList<>();

    public void addSheetContents(final List<SheetContent> contents) {
        this.contents.addAll(contents);
    }

    public List<SheetContent> getSheets() {
        return Collections.unmodifiableList(contents);
    }
}
