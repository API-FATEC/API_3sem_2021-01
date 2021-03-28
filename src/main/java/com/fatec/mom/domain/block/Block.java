package com.fatec.mom.domain.block;

import lombok.*;

import javax.persistence.*;

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
    @Column(name = "BLC_COD", nullable = false)
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
}
