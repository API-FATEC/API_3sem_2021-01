package com.fatec.mom.domain.tag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "MOM_TAG_TIPO")
@SequenceGenerator(name = "MOM_TAG_TIPO_SQ", sequenceName = "MOM_TAG_TIPO_SQ", allocationSize = 1)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TypeTag {

    @Id
    @GeneratedValue(generator = "MOM_TAG_TIPO_SQ", strategy = GenerationType.SEQUENCE)
    @Column(name = "TIP_COD")
    private Long id;

    @Column(name = "TIP_NOM", nullable = false)
    private String name;
}
