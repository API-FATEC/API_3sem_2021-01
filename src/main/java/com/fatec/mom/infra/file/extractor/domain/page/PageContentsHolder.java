package com.fatec.mom.infra.file.extractor.domain.page;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class PageContentsHolder {

    @Getter
    @Setter
    private List<PageContent> contents;
}
