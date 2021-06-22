package com.fatec.mom.domain.lep;

import lombok.Data;

@Data
public class ClasseLEP {
    private String block;
    private int code;
    private int page;
    private String change;

    public ClasseLEP(String block, int code, int page, String change) {
        this.block = block;
        this.code = code;
        this.page = page;
        this.change = change;
    }
}