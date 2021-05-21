package com.fatec.mom.infra.gitexecutor.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum GitAuxiliaryCommand {

    ADD_ALL("."),
    MESSAGE("-m"),
    NEW_BRANCH("-b"),
    PATH("-C"),
    ABBREV_REF("--abbrev-ref"),
    TAG_NAME("-a");

    @Getter
    private final String command;
}
