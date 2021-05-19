package com.fatec.mom.domain.block;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fatec.mom.domain.block.pagescomparator.changes.BlockPageChange;
import com.fatec.mom.domain.revision.Revision;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "MOM_BLOCK_PAGES")
@SequenceGenerator(name = "MOM_BLOCK_PAGES_SQ", sequenceName = "MOM_BLOCK_PAGES_SQ", allocationSize = 1)
@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
@ToString(of = {"id", "number"})
@EqualsAndHashCode(of = {"id", "number"})
public class BlockPage {

    @Id
    @GeneratedValue(generator = "MOM_BLOCK_PAGES_SQ", strategy = GenerationType.SEQUENCE)
    @Column(name = "PAG_COD")
    private Long id;

    @Column(name = "PAG_NUMBER", nullable = false)
    private Integer number;

    @JsonBackReference("pages")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BLC_COD")
    private Block block;

    @JsonBackReference("blocksInRevision")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REV_COD")
    private Revision revision;

    @OneToMany(
            mappedBy = "actualPage",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<BlockPageChange> changes;

    public void addChange(final BlockPageChange change) {
        if (changes == null) {
            changes = new HashSet<>();
        }

        changes.add(change);
        change.setActualPage(this);
    }
}
