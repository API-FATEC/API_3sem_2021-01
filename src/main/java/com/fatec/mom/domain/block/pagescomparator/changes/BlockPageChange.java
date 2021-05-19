package com.fatec.mom.domain.block.pagescomparator.changes;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fatec.mom.domain.block.BlockPage;
import com.fatec.mom.domain.block.pagescomparator.BlockPageStatus;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "MOM_REVISAO_PAGE_CHANGES")
@SequenceGenerator(name = "MOM_REVISAO_PAGE_CHANGES_SQ", sequenceName = "MOM_REVISAO_PAGE_CHANGES_SQ", allocationSize = 1)
@Getter @Setter @RequiredArgsConstructor
@EqualsAndHashCode
public class BlockPageChange {

    @Id
    @GeneratedValue(generator = "MOM_REVISAO_PAGE_CHANGES_SQ", strategy = GenerationType.SEQUENCE)
    @Column(name = "CHA_COD")
    private Long id;

    @JsonBackReference("changes")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PAG_COD")
    private BlockPage actualPage;

    @Column(name = "CHA_STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private BlockPageStatus status;

    @Column(name = "CHA_REVISED", nullable = false)
    private Boolean revised;

    @Column(name = "CHA_SAVED_DATE")
    private Date savedDate;

    public BlockPageChange(BlockPage page, BlockPageStatus status, boolean revised) {
        this.actualPage = page;
        this.status = status;
        this.revised = revised;
        this.savedDate = new Date();
    }

    public boolean isRevised() {
        return revised;
    }

    public static BlockPageChange createRevised(final BlockPage page) {
        return new BlockPageChange(page, BlockPageStatus.NOT_CHANGED, true);
    }

    public static BlockPageChange createNonRevised(final BlockPage page) {
        return new BlockPageChange(page, BlockPageStatus.NOT_CHANGED, false);
    }
}
