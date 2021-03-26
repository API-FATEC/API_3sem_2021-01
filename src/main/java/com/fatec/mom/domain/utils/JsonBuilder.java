package com.fatec.mom.domain.utils;

import com.google.gson.GsonBuilder;

public class JsonBuilder {

    public static String toJsonWithExcludeExpose(final Object obj) {
        GsonBuilder gsonBuilder = new GsonBuilder().disableHtmlEscaping();
        gsonBuilder.excludeFieldsWithoutExposeAnnotation();
        return gsonBuilder.create().toJson(obj);
    }
}
