package com.fatec.mom.domain.git.commands.branch;

import com.fatec.mom.domain.git.commands.branch.GitBranch;
import com.fatec.mom.domain.utils.Adapter;
import org.eclipse.jgit.lib.Ref;

public class GitBranchAdapter implements Adapter<GitBranch, Ref> {

    public GitBranch adapt(Ref ref) {
        return new GitBranch(ref.getName());
    }
}
