package com.fatec.mom.domain.revision;

public enum RevisionName {

    ORIGINAL,
    REVISION;

    public static String getRevName(Integer number) {
        return String.format("%s%d", REVISION.name(), number);
    }

    public static String getRevisionByString(String revision) {
        if (revision.equalsIgnoreCase(RevisionName.ORIGINAL.name())) {
            return revision;
        }
        revision = revision.replaceFirst("(?i)revision", "").replace(" ", "");
        final Integer number = Integer.parseInt(revision);
        return getRevName(number);
    }
}
