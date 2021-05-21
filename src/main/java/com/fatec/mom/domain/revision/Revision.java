package com.fatec.mom.domain.revision;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fatec.mom.domain.block.Block;
import com.fatec.mom.domain.block.BlockLink;
import com.fatec.mom.domain.document.Document;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "MOM_REVISAO")
@SequenceGenerator(sequenceName = "MOM_REVISAO_SQ", name = "MOM_REVISAO_SQ", allocationSize = 1)
@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
@ToString(of = {"id", "name", "status", "createdDate", "lastUpdateDate"})
@EqualsAndHashCode(of = {"id", "name", "status", "createdDate", "lastUpdateDate"})
public class Revision {

    @Id
    @GeneratedValue(generator = "MOM_REVISAO_SQ", strategy = GenerationType.SEQUENCE)
    @Column(name = "REV_COD")
    private Long id;

    @Column(name = "REV_NAME")
    private String name;

    @Column(name = "REV_STATUS")
    @Enumerated(EnumType.STRING)
    private RevisionStatus status;

    @Column(name = "REV_CREATED_DATE", nullable = false)
    private Date createdDate;

    @Column(name = "REV_ULTIMA_ATUALIZACAO")
    private Date lastUpdateDate;

    @JsonBackReference("revisions")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DOC_COD")
    private Document document;

    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinTable(name = "MOM_REVISAO_BLOCO",
            joinColumns = @JoinColumn(name = "REV_COD"),
            inverseJoinColumns = @JoinColumn(name = "BLC_COD"))
    private List<Block> blocksInRevision;

    @OneToMany(
            mappedBy = "revision",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<RevisionTag> revisionTags;

    public void addRevisionTag(RevisionTag tag) {
        if (revisionTags == null) {
            revisionTags = new HashSet<>();
        }
        revisionTags.add(tag);
        tag.setRevision(this);
    }
}
