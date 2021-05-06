package com.fatec.mom.infra.gitexecutor.handler;

import java.util.stream.Stream;

public interface GitHandlerListener {

    void onLineAvailable(Stream<String> line);
}
