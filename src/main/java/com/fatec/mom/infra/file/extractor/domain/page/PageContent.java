package com.fatec.mom.infra.file.extractor.domain.page;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PageContent {

    private final List<PageData> data;

    public PageData getData(PageData.PageDataType pageDataType) {
        return data.stream()
                .filter(pageData -> pageData.getDataType().equals(pageDataType))
                .findFirst().orElseThrow();
    }
}
