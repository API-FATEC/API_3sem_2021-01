package com.fatec.mom.infra.gitprocessor.gitprocess;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GitProcess {

    private Process process;
    private long startTime;
    private String[] commands;

}
