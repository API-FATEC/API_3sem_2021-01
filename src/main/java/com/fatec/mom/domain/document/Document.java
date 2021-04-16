package com.fatec.mom.domain.document;

import com.fatec.mom.domain.block.Block;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "MOM_DOCUMENTO")
@SequenceGenerator(sequenceName = "MOM_DOCUMENTO_SQ", name = "MOM_DOCUMENTO_SQ", allocationSize = 1)
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode(of = {"createdDate", "name", "partNumber", "trait"})
public class Document {

    @Id
    @GeneratedValue(generator = "MOM_DOCUMENTO_SQ", strategy = GenerationType.SEQUENCE)
    @Column(name = "DOC_COD", nullable = false)
    private Long id;

    @Column(name = "DOC_DATA_CRIA")
    private Date createdDate;

    @Getter
    @Column(name = "DOC_NOME", nullable = false)
    private String name;

    @Column(name = "DOC_PN", nullable = false)
    private Integer partNumber;

    @Column(name = "DOC_TRACO", nullable = false)
    private Integer trait;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinTable(name = "MOM_DOC_BLOCO",
        joinColumns = @JoinColumn(name = "DOC_COD"),
        inverseJoinColumns = @JoinColumn(name = "BLC_COD"))
    private Set<Block> blocks;

    public void addBlock(final Block block) {
        this.blocks.add(block);
    }

}
