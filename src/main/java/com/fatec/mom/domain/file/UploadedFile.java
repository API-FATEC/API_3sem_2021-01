package com.fatec.mom.domain.file;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "MOM_UPLOADED_FILES")
@SequenceGenerator(name = "MOM_UPLOADED_FILES_SQ", sequenceName = "MOM_UPLOADED_FILES_SQ", allocationSize = 1)
@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
@ToString
public class UploadedFile {

    @Id
    @GeneratedValue(generator = "MOM_UPLOADED_FILES_SQ", strategy = GenerationType.SEQUENCE)
    @Column(name = "UPL_COD")
    private Long id;

    @Column(name = "UPL_TYPE")
    private String type;

    @Column(name = "UPL_ORIGINAL_NAME")
    private String originalFileName;

    @Column(name = "UPL_FILE_NAME")
    private String fileName;

    @Column(name = "UPL_PATH")
    private String path;

    @Column(name = "UPL_EXT")
    private String extension;
}
