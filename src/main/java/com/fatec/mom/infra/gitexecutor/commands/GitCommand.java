package com.fatec.mom.infra.gitexecutor.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum GitCommand {

    ADD("add"),
    BRANCH("branch"),
    CHECKOUT("checkout"),
    COMMIT("commit"),
    VERSION("--version"),
    MERGE("merge"),
    STATUS("status"),
    REV_PARSE("rev-parse");

    @Getter
    private final String name;
}
