package com.fatec.mom.infra.generator.lep;

import lombok.Data;

@Data
public class ItensLEP {
    private String block;
    private int code;
    private int page;
    private String change;

    public ItensLEP(String block, int code, int page, String change) {
        this.block = block;
        this.code = code;
        this.page = page;
        this.change = change;
    }
}
