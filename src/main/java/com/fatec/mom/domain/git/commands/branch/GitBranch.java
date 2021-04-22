package com.fatec.mom.domain.git.commands.branch;

import lombok.Getter;
import org.eclipse.jgit.lib.ObjectId;

@Getter
public class GitBranch {

    private String name;
    private ObjectId objectId;

    public GitBranch(String name) {
        this.name = name;
    }

    public GitBranch(String name, ObjectId objectId) {
        this.name = name;
        this.objectId = objectId;
    }
}
