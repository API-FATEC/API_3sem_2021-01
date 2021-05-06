package com.fatec.mom.domain.tag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "MOM_TAG")
@SequenceGenerator(name = "MOM_TAG_SQ", sequenceName = "MOM_TAG_SQ", allocationSize = 1)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tag {

    @Id
    @GeneratedValue(generator = "MOM_TAG_SQ", strategy = GenerationType.SEQUENCE)
    @Column(name = "TAG_COD")
    private Long id;

    private String name;

    private Date created;

    @ManyToOne
    @JoinColumn(name = "TIP_COD")
    private TypeTag typeTag;
}
