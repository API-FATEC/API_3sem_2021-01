package com.fatec.mom.domain.block;

import com.fatec.mom.domain.document.Document;
import com.google.gson.annotations.Expose;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "MOM_BLOCO")
@SequenceGenerator(sequenceName = "MOM_BLOCO_SQ", name = "MOM_BLOCO_SQ", allocationSize = 1)
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode(of = {"section", "subSection", "number", "name", "code"})
@ToString(of = {"id", "section", "subSection", "number", "name", "code"})
public class Block {

    @Id
    @GeneratedValue(generator = "MOM_BLOCO_SQ", strategy = GenerationType.SEQUENCE)
    @Column(name = "BLC_COD", nullable = false)
    @Expose
    private Long id;

    @Expose
    @Column(name = "BLC_SECAO", nullable = false)
    private String section;

    @Expose
    @Column(name = "BLC_SUB_SECAO")
    private String subSection;

    @Expose
    @Column(name = "BLC_NUMERO", nullable = false)
    private Integer number;

    @Expose
    @Column(name = "BLC_NOME")
    private String name;

    @Expose
    @Column(name = "BLC_CODIGO", nullable = false)
    private Integer code;

    @ManyToMany(mappedBy = "blocks", cascade = CascadeType.ALL)
    private Set<Document> documents;
}
