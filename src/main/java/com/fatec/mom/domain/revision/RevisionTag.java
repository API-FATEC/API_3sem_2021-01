package com.fatec.mom.domain.revision;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "MOM_REVISAO_TAG")
@SequenceGenerator(name = "MOM_REVISAO_TAG_SQ",sequenceName = "MOM_REVISAO_TAG_SQ", allocationSize = 1)
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Builder
public class RevisionTag {

    @Id
    @GeneratedValue(generator = "MOM_REVISAO_TAG_SQ", strategy = GenerationType.SEQUENCE)
    @Column(name = "RTAG_COD")
    private Long id;

    @Column(name = "RTAG_VALUE")
    private String value;

    @JsonBackReference("revisionTags")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REV_COD")
    private Revision revision;

    public double incrementValue() {
        value = String.valueOf(Double.parseDouble(value) + 0.01);
        return Double.parseDouble(value);
    }
}
