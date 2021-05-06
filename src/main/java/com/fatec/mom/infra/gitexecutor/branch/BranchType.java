package com.fatec.mom.infra.gitexecutor.branch;

import java.util.Locale;

public enum BranchType {

    MASTER;

    public String getName() {
        return this.name().toLowerCase(Locale.ROOT);
    }
}
