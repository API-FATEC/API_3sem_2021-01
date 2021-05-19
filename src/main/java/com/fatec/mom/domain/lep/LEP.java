package com.fatec.mom.domain.lep;

import com.fatec.mom.domain.block.pagescomparator.changes.BlockPageChange;
import com.fatec.mom.domain.revision.Revision;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LEP implements Serializable {

    private Set<Revision> revisions;
    private List<BlockPageChange> pages;
}
