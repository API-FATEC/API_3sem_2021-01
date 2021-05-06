package com.fatec.mom.test.infra.processor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.logging.LogLevel;

import java.io.*;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Slf4j(topic = "CreateGitProcessTest")
public class CreateGitProcessTest {

    static String GIT_PATH = "C:/Program Files/Git/bin/git.exe";
    static String COMMAND_PATH = "-C";
    static String REPOSITORY_PATH = "D:/Documents/facul/PI_III/git-repository";
    static String GIT_BRANCH_COMMAND = "branch";
    static String GIT_ADD_COMMAND = "add";
    static String GIT_ADD_ALL_COMMAND = ".";
    static String GIT_COMMIT_COMMAND = "cm";
    static String GIT_COMMIT_MESSAGE = "Java process Commit Message";
    static String GIT_STATUS_COMMAND = "status";

    private static final long LONG_TIME = 10 * 1000;

    @Test
    void createGitBranchProcess() throws IOException, InterruptedException {
        final String[] command = new String[] {GIT_PATH, COMMAND_PATH, REPOSITORY_PATH, GIT_BRANCH_COMMAND};
        System.out.println(Arrays.toString(command));

        final ProcessBuilder processBuilder = new ProcessBuilder(command);
        final Process myProcess = processBuilder.start();

       printOutput(myProcess);
    }

    @Test
    void createComplexGitProcess() throws IOException, InterruptedException {
        final String[] addCommand = new String[] {GIT_PATH, COMMAND_PATH, REPOSITORY_PATH, GIT_ADD_COMMAND, GIT_ADD_ALL_COMMAND};
        final String[] commitCommand = new String[] {GIT_PATH, COMMAND_PATH, REPOSITORY_PATH, GIT_COMMIT_COMMAND, GIT_COMMIT_MESSAGE};
        final String[] statusCommand = new String[] {GIT_PATH, COMMAND_PATH, REPOSITORY_PATH, GIT_STATUS_COMMAND};
        ProcessBuilder processBuilder = new ProcessBuilder(addCommand);
        Process process = processBuilder.start();
        printOutput(process);

        processBuilder = new ProcessBuilder(commitCommand);
        process = processBuilder.start();
        printOutput(process);

        processBuilder = new ProcessBuilder(statusCommand);
        process = processBuilder.start();
        printOutput(process);
    }

    @Test
    void createGitStatusProcess() throws IOException, InterruptedException {
        final long start = System.currentTimeMillis();
        final String[] statusCommand = new String[] {GIT_PATH, COMMAND_PATH, REPOSITORY_PATH, GIT_STATUS_COMMAND};
        ProcessBuilder builder = new ProcessBuilder(statusCommand);
        Process process = builder.start();

        printOutput(process);
        logTime(start, Arrays.toString(statusCommand));
    }

    @Test
    void getGitVersion() throws IOException, InterruptedException {
        final String[] command = new String[] {GIT_PATH, "--version"};
        final ProcessBuilder processBuilder = new ProcessBuilder(command);
        final Process process = processBuilder.start();

        printOutput(process);
    }

    void printOutput(final Process process) throws InterruptedException, IOException {
        if (process.waitFor(3, TimeUnit.SECONDS)) {
            final BufferedReader output = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = output.readLine();
            while (line != null) {
                System.out.println(line);
                line = output.readLine();
            }
        }
    }

    void logTime(long startTime, String myCommand) {
        if (startTime > 0) {
            long time = System.currentTimeMillis() - startTime;
            if (!log.isDebugEnabled() && time > LONG_TIME) {
                log.info(String.format("%s took %s ms.", myCommand, time));
            }
            else {
                log.debug(String.format("git %s took %s ms", myCommand, time));
            }
        }
        else {
            log.debug(String.format("git %s finished.", myCommand));
        }
    }
}
