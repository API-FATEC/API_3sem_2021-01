package com.fatec.mom.infra.gitexecutor;

import com.fatec.mom.infra.gitexecutor.commands.GitCommandResult;
import com.fatec.mom.infra.gitexecutor.handler.GitHandler;
import com.fatec.mom.infra.gitexecutor.handler.GitHandlerListener;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class GitImplBase implements Git {

    private static class OutputCollector {

        final List<String> output = new LinkedList<>();

        void addLine(@NotNull Stream<String> line) {
            synchronized (output) {
                output.addAll(line.collect(Collectors.toList()));
            }
        }

        void outputReceived(@NotNull Stream<String> line) {
            addLine(line);
        }
    }

    @RequiredArgsConstructor
    @Getter
    private static class GitCommandResultListener implements GitHandlerListener {

        private final OutputCollector outputCollector;

        private int exitCode = 0;
        private boolean startFailed = false;

        @Override
        public void onLineAvailable(Stream<String> line) {
            outputCollector.outputReceived(line);
        }
    }

    protected static GitCommandResult run(@NotNull GitHandler handler,
                                          @NotNull OutputCollector outputCollector) {
        GitCommandResultListener resultListener = new GitCommandResultListener(outputCollector);
        handler.addListener(resultListener);

        try {
            handler.run();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new GitCommandResult(resultListener.startFailed,
                                    resultListener.exitCode,
                                    outputCollector.output);
    }

    protected static OutputCollector getOutputCollector() {
        return new OutputCollector();
    }
}
