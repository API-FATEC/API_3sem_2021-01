package com.fatec.mom.domain.document;

import com.fatec.mom.domain.block.Block;
import com.fatec.mom.domain.revision.Revision;
import com.fatec.mom.domain.tag.Tag;
import com.fatec.mom.domain.trait.Trait;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "MOM_DOCUMENTO")
@SequenceGenerator(sequenceName = "MOM_DOCUMENTO_SQ", name = "MOM_DOCUMENTO_SQ", allocationSize = 1)
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode(of = {"name", "partNumber"})
public class Document {

    @Id
    @GeneratedValue(generator = "MOM_DOCUMENTO_SQ", strategy = GenerationType.SEQUENCE)
    @Column(name = "DOC_COD")
    private Long id;

    @Column(name = "DOC_DATA_CRIA")
    private Date createdDate;

    @Column(name = "DOC_NOME", nullable = false)
    private String name;

    @Column(name = "DOC_PN", nullable = false)
    private Integer partNumber;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Revision> revisions;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "MOM_TAG_DOC",
            joinColumns = @JoinColumn(name = "DOC_COD"),
            inverseJoinColumns = @JoinColumn(name = "TAG_COD"))
    private Set<Tag> tags;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "MOM_TRACO_DOC",
            joinColumns = @JoinColumn(name = "DOC_COD"),
            inverseJoinColumns = @JoinColumn(name = "TRA_COD"))
    private Set<Trait> traits;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "MOM_BLOCO_DOC",
                joinColumns = @JoinColumn(name = "DOC_COD"),
                inverseJoinColumns = @JoinColumn(name = "BLC_COD"))
    private Set<Block> blocks;

    public String getDocument() {
        return String.format("%s-%s", name, partNumber);
    }

    public boolean containsTrait(Integer trait) {
        return traits.stream().map(trait1 -> trait1.getNumber() == trait).collect(Collectors.toSet()).size() > 0;
    }

    public void addBlock(final Block block) {
        this.blocks.add(block);
    }
}
