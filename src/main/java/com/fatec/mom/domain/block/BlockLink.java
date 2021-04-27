package com.fatec.mom.domain.block;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "MOM_BLOCO_LINK")
@SequenceGenerator(sequenceName = "MOM_BLOCO_LINK_SQ", name = "MOM_BLOCO_LINK_SQ", allocationSize = 1)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlockLink {

    @Id
    @GeneratedValue(generator = "MOM_BLOCO_LINK_SQ", strategy = GenerationType.SEQUENCE)
    @Column(name = "LNK_COD")
    private Long id;

    @Column(name = "LNK_FILENAME", nullable = false)
    private String fileName;

    @Column(name = "LNK_EXT")
    private String extension;

    @Column(name = "LNK_UPLOAD_DATE", nullable = false)
    private Date upload;

    @ManyToOne
    @JoinColumn(name = "BLC_COD")
    private Block block;
}
