package com.fatec.mom.domain.block;

import com.fatec.mom.domain.document.Document;
import com.fatec.mom.domain.tag.Tag;
import com.fatec.mom.domain.trait.Trait;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * A classe <code>Block</code> representa a entidade MOM_BLOCO do banco de dados.
 *
 * @author Tobias Lino
 * @version v01 26/03/2021
 */
@Entity
@Table(name = "MOM_BLOCO")
@SequenceGenerator(sequenceName = "MOM_BLOCO_SQ", name = "MOM_BLOCO_SQ", allocationSize = 1)
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode(of = {"section", "subSection", "number", "name", "code"})
public class Block {

    @Id
    @GeneratedValue(generator = "MOM_BLOCO_SQ", strategy = GenerationType.SEQUENCE)
    @Column(name = "BLC_COD")
    private Long id;

    @Column(name = "BLC_SECAO", nullable = false)
    private String section;

    @Column(name = "BLC_SUB_SECAO")
    private String subSection;

    @Column(name = "BLC_NUMERO", nullable = false)
    private Integer number;

    @Column(name = "BLC_NOME")
    private String name;

    @Column(name = "BLC_CODIGO", nullable = false)
    private Integer code;

    @Column(name = "BLC_ORDER")
    private Integer order;

    @Column(name = "BLC_STATUS")
    @Enumerated(EnumType.STRING)
    private BlockStatus status;

    @Column(name = "BLC_BASEPATH", nullable = false)
    private String basePath;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<BlockLink> links;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "MOM_TAG_BLC",
            joinColumns = @JoinColumn(name = "BLC_COD"),
            inverseJoinColumns = @JoinColumn(name = "TAG_COD"))
    private Set<Tag> tags;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "MOM_TRACO_BLC",
            joinColumns = @JoinColumn(name = "BLC_COD"),
            inverseJoinColumns = @JoinColumn(name = "TRA_COD"))
    private Set<Trait> traits;

    public String getBlockName(Document document) {
        if (getSubSection() == null) {
            return String.format("%s-%s-%sc%s",
                                document.getDocument(),
                                getSection(),
                                getNumber(),
                                getCode());
        }
        return String.format("%s-%s-%s-%s-c%s",
                                document.getDocument(),
                                getSection(),
                                getSubSection(),
                                getNumber(),
                                getCode());
    }

    public void addTrait(Trait trait) {
        traits.add(trait);
    }

    public boolean hasTrait(Trait trait) {
        return traits.contains(trait);
    }
}
