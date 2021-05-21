package com.fatec.mom.infra.gitexecutor.commands;

import com.fatec.mom.domain.document.DocumentDescriptor;
import com.fatec.mom.infra.gitexecutor.config.GitProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class GitCommandAdapter {

    private static final String HEAD = "HEAD";

    @Autowired
    private GitProperties gitProperties;

    public String[] adaptCheckoutCommand(@NotNull final DocumentDescriptor descriptor, final String branchName) {
        return new String[] {
                getExecutableCommand(),
                GitAuxiliaryCommand.PATH.getCommand(),
                descriptor.getDocumentPath(),
                GitCommand.CHECKOUT.getName(),
                branchName
        };
    }

    public String[] adaptCheckoutNewBranchCommand(@NotNull final DocumentDescriptor descriptor, final String branchName) {
        return new String[] {
                getExecutableCommand(),
                GitAuxiliaryCommand.PATH.getCommand(),
                descriptor.getDocumentPath(),
                GitCommand.CHECKOUT.getName(),
                GitAuxiliaryCommand.NEW_BRANCH.getCommand(),
                branchName
        };
    }

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

    public String[] adaptInitCommand(final DocumentDescriptor descriptor) {
        return new String[] {
                getExecutableCommand(),
                GitAuxiliaryCommand.PATH.getCommand(),
                descriptor.getDocumentPath(),
                GitCommand.INIT.getName()
        };
    }

    public String[] adaptCommitCommand(final DocumentDescriptor descriptor, final String message) {
        return new String[] {
                getExecutableCommand(),
                GitAuxiliaryCommand.PATH.getCommand(),
                descriptor.getDocumentPath(),
                GitCommand.COMMIT.getName(),
                GitAuxiliaryCommand.MESSAGE.getCommand(),
                String.format("\"%s\"", message)
        };
    }

    public String[] adaptTagCommand(final DocumentDescriptor descriptor,
                                    final String tag,
                                    final String message) {
        return new String[] {
                getExecutableCommand(),
                GitAuxiliaryCommand.PATH.getCommand(),
                descriptor.getDocumentPath(),
                GitCommand.TAG.getName(),
                GitAuxiliaryCommand.TAG_NAME.getCommand(),
                tag,
                GitAuxiliaryCommand.MESSAGE.getCommand(),
                String.format("\"%s\"", message)
        };
    }
}
