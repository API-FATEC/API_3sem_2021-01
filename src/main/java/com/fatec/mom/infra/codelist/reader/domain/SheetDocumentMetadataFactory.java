package com.fatec.mom.infra.codelist.reader.domain;

import java.util.Arrays;
import java.util.List;

public class SheetDocumentMetadataFactory {

    public static String getDocumentNameFrom(String sheetName) {
        String[] split = sheetName.split("-");
        String result = split.length > 0 ? split[0].replace(" ", "_") : "";
        List<String> resultNames = Arrays.asList(result.split("_"));
        StringBuilder nameBuilder = new StringBuilder();
        for (int i = 0; i < resultNames.size(); i++) {
            var name = resultNames.get(i);
            nameBuilder.append(name);
            if (i != resultNames.size()-1) {
                nameBuilder.append(" ");
            }
        }
        return nameBuilder.toString();
    }

    public static Integer getTraitFrom(String sheetName) {
        var limiter = sheetName.indexOf(" - ");
        return Integer.parseInt(sheetName.substring(0, limiter));
    }

    public static Integer getPartNumberFrom(String sheetName) {
        String[] splitted = sheetName.split("-");
        return Integer.parseInt(splitted.length > 1 ? splitted[1].replace(" ", "") : "0");
    }

}
