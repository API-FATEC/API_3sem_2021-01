package com.fatec.mom.domain.document;

import com.fatec.mom.domain.block.Block;
import com.fatec.mom.domain.revision.Revision;
import com.fatec.mom.domain.tag.Tag;
import com.fatec.mom.domain.trait.Trait;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "MOM_DOCUMENTO")
@SequenceGenerator(sequenceName = "MOM_DOCUMENTO_SQ", name = "MOM_DOCUMENTO_SQ", allocationSize = 1)
@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
@ToString(of = {"id", "createdDate", "name", "partNumber"})
@EqualsAndHashCode(of = {"id", "createdDate", "name", "partNumber"})
public class Document implements Serializable {

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

    @OneToMany(
            mappedBy = "document",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Revision> revisions;

    public void addRevision(Revision revision) {
        if (revisions == null) {
            revisions = new HashSet<>();
        }
        revisions.add(revision);
        revision.setDocument(this);
    }

    public void removeRevision(Revision revision) {
        if (revisions != null) {
            revisions.remove(revision);
            revision.setDocument(null);
        }
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "MOM_TAG_DOC",
            joinColumns = @JoinColumn(name = "DOC_COD"),
            inverseJoinColumns = @JoinColumn(name = "TAG_COD"))
    private Set<Tag> tags;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "MOM_TRACO_DOC",
            joinColumns = @JoinColumn(name = "DOC_COD"),
            inverseJoinColumns = @JoinColumn(name = "TRA_COD"))
    private Set<Trait> traits;

    public void addTrait(Trait trait) {
        if (traits == null) {
            traits = new HashSet<>();
        }
        traits.add(trait);
    }

    public void removeTrait(Trait trait) {
        if (traits != null) {
            traits.remove(trait);
        }
    }

    @OneToMany(
            mappedBy = "document",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Block> blocks;

    public void addBlock(Block block) {
        if (blocks == null) {
            blocks = new HashSet<>();
        }
        blocks.add(block);
        block.setDocument(this);
    }

    public void removeBlock(Block block) {
        if (blocks != null) {
            blocks.remove(block);
            block.setDocument(null);
        }
    }

    public String getDocument() {
        return String.format("%s-%s", name, partNumber);
    }

    public boolean containsTrait(Integer trait) {
        return traits.stream().map(trait1 -> trait1.getNumber().equals(trait)).collect(Collectors.toSet()).size() > 0;
    }
}
