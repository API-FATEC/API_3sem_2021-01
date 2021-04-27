package com.fatec.mom.domain.block;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    public static class FilePathBuilder {

        @Value("${default-documents-path}")
        private String DOCUMENTS_PATH;

        public String getCompleteFilePathFromMaster(@NotNull final BlockLink link) {
            return String.format("%s/Master/%s/%s",
                    DOCUMENTS_PATH,
                    link.block.getBlockName(),
                    link.fileName);
        }

        public String getCompleteFilePathFromRev(@NotNull final BlockLink link, final String revPath) {
            return String.format("%s/Rev/%s/%s/%s",
                    DOCUMENTS_PATH,
                    revPath,
                    link.block.getBlockName(),
                    link.fileName);
        }
    }
}
