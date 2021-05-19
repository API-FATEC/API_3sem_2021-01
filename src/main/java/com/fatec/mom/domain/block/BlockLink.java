package com.fatec.mom.domain.block;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fatec.mom.domain.document.Document;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "MOM_BLOCO_LINK")
@SequenceGenerator(sequenceName = "MOM_BLOCO_LINK_SQ", name = "MOM_BLOCO_LINK_SQ", allocationSize = 1)
@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
@ToString(of = {"id", "fileName", "extension", "upload"})
@EqualsAndHashCode(of = {"id", "fileName", "extension", "upload"})
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

    @JsonBackReference("links")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BLC_COD")
    private Block block;

    public static class FilePathBuilder {

        @Value("${default-documents-path}")
        private String DOCUMENTS_PATH;

        public String getCompleteFilePathFromMaster(@NotNull final BlockLink link, @NotNull final Block block, @NotNull final Document document) {
            return String.format("%s/Master/%s/%s",
                    DOCUMENTS_PATH,
                    block.getBlockName(document),
                    link.fileName + link.extension);
        }

        public String getCompleteFilePathFromRev(@NotNull final BlockLink link, @NotNull final Block block, @NotNull final Document document, final String revPath) {
            return String.format("%s/Rev/%s/%s/%s",
                    DOCUMENTS_PATH,
                    revPath,
                    block.getBlockName(document),
                    link.fileName + link.extension);
        }
    }
}
