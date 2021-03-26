package com.fatec.mom.domain.document;

import com.fatec.mom.domain.block.Block;
import com.google.gson.annotations.Expose;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    @Expose
    private Long id;

    @Expose
    @Column(name = "DOC_DATA_CRIA")
    private LocalDateTime createdDate;

    @Expose
    @Column(name = "DOC_NOME", nullable = false)
    private String name;

    @Expose
    @Column(name = "DOC_PN", nullable = false)
    private Integer partNumber;

    @Expose
    @Column(name = "DOC_TRACO", nullable = false)
    private Integer trait;

    @Expose
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "MOM_DOC_BLOCO",
        joinColumns = @JoinColumn(name = "DOC_COD"),
        inverseJoinColumns = @JoinColumn(name = "BLC_COD"))
    private Set<Block> blocks;
}
