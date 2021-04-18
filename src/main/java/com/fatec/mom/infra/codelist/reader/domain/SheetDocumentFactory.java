package com.fatec.mom.infra.codelist.reader.domain;

public class SheetDocumentFactory {

    public static String getDocumentNameFrom(String sheetName) {
        var limiter = sheetName.indexOf("-");
        return sheetName.substring(0, limiter);
    }

    public static Integer getTraitFrom(String sheetName) {
        var limiter = sheetName.indexOf(" - ");
        return Integer.parseInt(sheetName.substring(0, limiter));
    }

    public static Integer getPartNumberFrom(String sheetName) {
        var limiter = sheetName.lastIndexOf("-");
        return Integer.parseInt(sheetName.substring(limiter));
    }

}
