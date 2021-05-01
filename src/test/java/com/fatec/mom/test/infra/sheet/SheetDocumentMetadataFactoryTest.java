package com.fatec.mom.test.infra.sheet;

import com.fatec.mom.infra.codelist.reader.domain.SheetDocumentMetadataFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class SheetDocumentMetadataFactoryTest {

    @DisplayName("Get document name from sheet name")
    @ParameterizedTest
    @ValueSource(strings = {
            "ABC-1234", "ABC -1234", "ABC- 1234",
            "ABC - 1234", "ABC  -  1234",
            "Simple document Test - 1234", "Simple Document1-2021 - 1234",
            "ABC -"})
    void getDocumentNameFromSheetName(String sheetName) {
        final var name = SheetDocumentMetadataFactory.getDocumentNameFrom(sheetName);
        System.out.printf("%s -> %s\n", name, name.length());
    }

    @ParameterizedTest
    @DisplayName("Get part number from sheet name")
    @ValueSource(strings = {
            "ABC-1234", "ABC -1234", "ABC- 1234",
            "ABC - 1234", "ABC  -  1234",
            "Simple document Test - 1234", "Simple Document1-2021 - 1234",
            "ABC -"})
    void getPartNumberFromSheetName(String sheetName) {
        final var name = SheetDocumentMetadataFactory.getPartNumberFrom(sheetName);
        System.out.println(name);
    }
}
