package com.fatec.mom.infra.gitprocessor;

import java.io.IOException;

public class ProcessManager {

    public static Process run(final String[] commands) throws IOException {
        final ProcessBuilder builder = new ProcessBuilder(commands);
        return builder.start();
    }
}
