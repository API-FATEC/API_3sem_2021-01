package com.fatec.mom.domain.revision;

public enum RevisionName {

    ORIGINAL,
    REV;

    public static String getRevName(Integer number) {
        return String.format("%s%d", REV.name(), number);
    }
}
