package com.fatec.mom.infra.gitexecutor.handler;

import com.fatec.mom.infra.gitexecutor.commands.GitCommand;
import com.fatec.mom.infra.gitprocessor.ProcessManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Slf4j(topic = "GitHandler")
public class GitHandler {

    private static final long LONG_TIME = 10 * 1000;

    private Process process;

    private GitCommand command;
    private final String[] commands;
    private long startTime;

    private Set<GitHandlerListener> listeners = new HashSet<>();


    public GitHandler(GitCommand command,
                      String[] commands) {
        this.command = command;
        this.commands = commands;
    }

    public void run() throws IOException {
        try {
            start();
            if (isStarted()) {
                printOutput();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            logTime();
        }
    }

    private void start() {
        try {
            this.startTime = System.currentTimeMillis();
            log.info("Running: " + Arrays.toString(commands));
            this.process = ProcessManager.run(this.commands);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isStarted() {
        return this.process != null;
    }

    private void logTime() {
        if (startTime > 0) {
            long time = System.currentTimeMillis() - startTime;
            if (!log.isDebugEnabled() && time > LONG_TIME) {
                log.info(String.format("git %s took %s ms. Command parameters: %n%s", command.getName(), time, Arrays.toString(commands)));
            }
            else {
                log.debug(String.format("git %s took %s ms", command.getName(), time));
            }
        }
        else {
            log.debug(String.format("git %s finished.", command.getName()));
        }
    }

    private void printOutput() throws InterruptedException {
        if (process.waitFor(3, TimeUnit.SECONDS)) {
            final BufferedReader output = new BufferedReader(new InputStreamReader(process.getInputStream()));
            listeners.forEach(listener -> {
                listener.onLineAvailable(output.lines());
            });
        }
    }

    public void addListener(@NotNull GitHandlerListener listener) {
        listeners.add(listener);
    }
}
