package com.fatec.mom.infra.codelist.reader.config;

import com.fatec.mom.infra.codelist.reader.codelistBuilder.CodelistBuiderInvoker;

public class SingleConfig extends CodelistConfig {

    private final CodelistSheetConfigPerTab config;

    public SingleConfig(CodelistBuiderInvoker invoker, CodelistSheetConfigPerTab config) {
        super(invoker);
        this.config = config;
    }

    public int getInitialIndex() {
        return config.getIndex();
    }

    public CodelistSheetConfig getConfig() {
        return config.getConfig();
    }
}
