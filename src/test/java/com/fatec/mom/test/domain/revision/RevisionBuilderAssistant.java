package com.fatec.mom.test.domain.revision;

import com.fatec.mom.domain.block.Block;
import com.fatec.mom.domain.revision.Revision;
import com.fatec.mom.domain.revision.RevisionName;
import com.fatec.mom.domain.revision.RevisionStatus;

import java.util.Date;
import java.util.List;

public class RevisionBuilderAssistant {

    public static Revision originalFromBlock(final Block block) {
        return Revision.builder()
                .name(RevisionName.ORIGINAL.name())
                .status(RevisionStatus.FINISHED)
                .blocksInRevision(List.of(block))
                .document(block.getDocument())
                .lastUpdateDate(new Date())
                .createdDate(new Date())
                .build();
    }

    private static Revision simpleFromBlock(final int version, final RevisionStatus status, final Block block) {
        return Revision.builder()
                .name(RevisionName.getRevName(version))
                .status(status)
                .blocksInRevision(List.of(block))
                .document(block.getDocument())
                .lastUpdateDate(new Date())
                .createdDate(new Date())
                .build();
    }

    public static Revision simpleOpenedFromBlock(final int version, final Block block) {
        return simpleFromBlock(version, RevisionStatus.OPENED, block);
    }

    public static Revision simpleFinishedFromBlock(final int version, final Block block) {
        return simpleFromBlock(version, RevisionStatus.FINISHED, block);
    }
}
