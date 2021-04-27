package com.fatec.mom.domain.revision;

import com.fatec.mom.domain.block.Block;
import com.fatec.mom.domain.document.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "MOM_REVISAO")
@SequenceGenerator(sequenceName = "MOM_REVISAO_SQ", name = "MOM_REVISAO_SQ", allocationSize = 1)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Revision {

    @Id
    @GeneratedValue(generator = "MOM_REVISAO_SQ", strategy = GenerationType.SEQUENCE)
    @Column(name = "REV_COD")
    private Long id;

    @Column(name = "REV_NAME")
    private String name;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "DOC_COD", nullable = false)
    private Document document;

    @Column(name = "REV_STATUS")
    @Enumerated(EnumType.STRING)
    private RevisionStatus status;

    @Column(name = "REV_CREATED_DATE", nullable = false)
    private Date createdDate;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinTable(name = "MOM_REVISAO_BLOCO",
            joinColumns = @JoinColumn(name = "REV_COD"),
            inverseJoinColumns = @JoinColumn(name = "BLC_COD"))
    private List<Block> blocksInRevision;
}