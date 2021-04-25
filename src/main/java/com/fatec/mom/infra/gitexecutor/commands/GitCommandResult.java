package com.fatec.mom.infra.gitexecutor.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class GitCommandResult {

    private final boolean startFailed;
    private final int exitCode;
    private final List<String> output;

    public boolean success() {
        return !startFailed && exitCode == 0;
    }
}
