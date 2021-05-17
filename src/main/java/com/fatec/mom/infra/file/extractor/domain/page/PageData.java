package com.fatec.mom.infra.file.extractor.domain.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PageData {

    private PageDataType dataType;
    private String data;

    public enum PageDataType {
        CODE,
        REVISION,
        PAGE
    }
}
