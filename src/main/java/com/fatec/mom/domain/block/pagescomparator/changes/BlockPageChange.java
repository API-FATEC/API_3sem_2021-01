package com.fatec.mom.domain.block.pagescomparator.changes;

import com.fatec.mom.domain.block.BlockPage;
import com.fatec.mom.domain.block.pagescomparator.BlockPageStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class BlockPageChange {

    @Getter
    private BlockPage oldPage;
    @Getter
    private BlockPage actualPage;

    @Getter
    @Setter
    private BlockPageStatus status;

    private Boolean revised;

    public boolean isRevised() {
        return revised;
    }
}
