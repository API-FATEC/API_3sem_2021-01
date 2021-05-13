package com.fatec.mom.domain.block;

import com.fatec.mom.domain.revision.Revision;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "MOM_BLOCK_PAGES")
@SequenceGenerator(name = "MOM_BLOCK_PAGES_SQ", sequenceName = "MOM_BLOCK_PAGES_SQ", allocationSize = 1)
@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class BlockPage {

    @Id
    @GeneratedValue(generator = "MOM_BLOCK_PAGES_SQ", strategy = GenerationType.SEQUENCE)
    @Column(name = "PAG_COD")
    private Long id;

    @Column(name = "PAG_NUMBER", nullable = false)
    private Integer number;

    @ManyToOne
    @JoinColumn(name = "BLC_COD")
    private Block block;

    @ManyToOne
    @JoinColumn(name = "REV_COD")
    private Revision revision;
}
