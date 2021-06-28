package com.fatec.mom.domain.block;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fatec.mom.domain.document.Document;
import com.fatec.mom.domain.tag.Tag;
import com.fatec.mom.domain.trait.Trait;
import com.google.common.base.Strings;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
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
@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
@ToString(of = {"id", "section", "subSection", "number", "name", "code", "order", "status"})
@EqualsAndHashCode(of = {"id", "section", "subSection", "number", "name", "code", "order", "status"})
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

    @JsonBackReference("blocks")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DOC_COD")
    private Document document;

    @Column(name = "BLC_BASEPATH", nullable = false)
    private String basePath;

    @OneToMany(
            mappedBy = "block",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<BlockLink> links;

    public void addLink(BlockLink blockLink) {
        if (links == null) {
            links = new HashSet<>();
        }
        links.add(blockLink);
        blockLink.setBlock(this);
    }

    public void removeLink(BlockLink blockLink) {
        if (links != null) {
            links.remove(blockLink);
            blockLink.setBlock(null);
        }
    }

    @OneToMany(
            mappedBy = "block",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<BlockPage> pages;

    public void addPage(BlockPage page) {
        if (pages == null) {
            pages = new HashSet<>();
        }
        pages.add(page);
        page.setBlock(this);
    }

    public void addAllPages(final List<BlockPage> pages) {
        for (final BlockPage page : pages) {
            addPage(page);
        }
    }

    public void removePage(BlockPage page) {
        if (pages != null) {
            pages.remove(page);
            page.setBlock(null);
        }
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "MOM_TAG_BLC",
            joinColumns = @JoinColumn(name = "BLC_COD"),
            inverseJoinColumns = @JoinColumn(name = "TAG_COD"))
    private Set<Tag> tags;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "MOM_TRACO_BLC",
            joinColumns = @JoinColumn(name = "BLC_COD"),
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

    public boolean hasTrait(Trait trait) {
        return traits.contains(trait);
    }

    public String getBlockName(Document document) {
        if (Strings.isNullOrEmpty(getSubSection())) {
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
}
