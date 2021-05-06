package com.fatec.mom.infra.gitexecutor.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class GitProperties {

    @Value("${git.default-executable-path}")
    private String gitExecutablePath;

    @Value("${git.default-executable}")
    private String gitExecutable;
}
