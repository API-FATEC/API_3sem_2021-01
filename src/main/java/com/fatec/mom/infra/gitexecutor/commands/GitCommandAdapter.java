package com.fatec.mom.infra.gitexecutor.commands;

import com.fatec.mom.domain.document.DocumentDescriptor;
import com.fatec.mom.infra.gitexecutor.config.GitProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GitCommandAdapter {

    private static final String HEAD = "HEAD";

    @Autowired
    private GitProperties gitProperties;

    public String[] adaptAddAllCommand(final DocumentDescriptor descriptor) {
        return new String[] {
                getExecutableCommand(),
                GitAuxiliaryCommand.PATH.getCommand(),
                descriptor.getDocumentPath(),
                GitCommand.ADD.getName(),
                GitAuxiliaryCommand.ADD_ALL.getCommand()
        };
    }

    public String[] adaptActualBranchCommand(final DocumentDescriptor descriptor) {
        return new String[] {
            getExecutableCommand(),
                GitAuxiliaryCommand.PATH.getCommand(),
                descriptor.getDocumentPath(),
                GitCommand.REV_PARSE.getName(),
                GitAuxiliaryCommand.ABBREV_REF.getCommand(),
                HEAD
        };
    }

    private String getExecutableCommand() {
        return String.format("%s/%s", gitProperties.getGitExecutablePath(), gitProperties.getGitExecutable());
    }
}
