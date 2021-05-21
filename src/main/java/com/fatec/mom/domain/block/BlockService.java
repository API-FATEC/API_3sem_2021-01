package com.fatec.mom.domain.block;

import com.fatec.mom.domain.file.FileUploadService;
import com.fatec.mom.domain.revision.Revision;
import com.fatec.mom.domain.revision.RevisionService;
import com.fatec.mom.infra.generator.RevisionManipulator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class BlockService {

    private static final String PDF_TYPE = "application/pdf";

    private final FileUploadService fileUploadService;

    private final BlockRepository blockRepository;

    private final BlockImporterService blockImporterService;

    private final RevisionService revisionService;

    private final RevisionManipulator revisionManipulator;

    @Autowired
    public BlockService(FileUploadService fileUploadService,
                        BlockRepository blockRepository,
                        BlockImporterService blockImporterService,
                        RevisionService revisionService,
                        RevisionManipulator revisionManipulator) {
        this.fileUploadService = fileUploadService;
        this.blockRepository = blockRepository;
        this.blockImporterService = blockImporterService;
        this.revisionService = revisionService;
        this.revisionManipulator = revisionManipulator;
    }

    public Block handleImport(final Long revisionId,
                              final Long blockId,
                              final List<MultipartFile> multipartFiles) throws IOException {
        final var block = blockRepository.findById(blockId);
        final var revision = revisionService.findById(revisionId);

        return handleImport(revision, block.orElseThrow(), multipartFiles);
    }

    public Block handleImport(final Revision revision,
                              final Block block,
                              final List<MultipartFile> multipartFiles) throws IOException {
        if (!multipartFiles.isEmpty()) {
            for (final MultipartFile file : multipartFiles) {
                final var savedFile = fileUploadService.uploadFile(file, revision);
                if (isPdf(file)) {
                    final var pages = getPages(block, savedFile);
                    block.addAllPages(pages);
                    block.setBasePath(savedFile.getAbsolutePath());
                }
            }
            commitChanges(revision, block);
        } else {
            log.info("Não foi passado nenhum arquivo");
        }

        revisionService.saveUpdateDate(revision);
        return save(block);
    }

    private void commitChanges(final Revision revision, final Block block) {
        revisionManipulator.checkoutToRevision(revision);
        if (revisionManipulator.isIn(revision)) {
            revisionManipulator.commitAllChanges(revision, block.getBlockName(block.getDocument()));
        }
    }

    @Transactional
    public List<BlockPage> getPages(final Block block,
                                     final File file) throws IOException {
        final var pages = blockImporterService.importBlock(block, file);
        if (!pages.isEmpty()) {
            return pages;
        }

        log.error("Não foi possível obter as páginas do bloco");
        return Collections.emptyList();
    }

    private boolean isPdf(final MultipartFile file) {
        return Objects.requireNonNull(file.getContentType()).equalsIgnoreCase(PDF_TYPE);
    }

    @Transactional
    public Block save(final Block block) {
        return blockRepository.save(block);
    }
}
