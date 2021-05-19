package com.fatec.mom.domain.block;

import com.fatec.mom.domain.file.FileUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
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

    @Autowired
    public BlockService(FileUploadService fileUploadService, BlockRepository blockRepository, BlockImporterService blockImporterService) {
        this.fileUploadService = fileUploadService;
        this.blockRepository = blockRepository;
        this.blockImporterService = blockImporterService;
    }

    public Block handleImport(final Long blockId,
                              final List<MultipartFile> multipartFiles) throws IOException {
        final var block = blockRepository.findById(blockId);
        if (block.isEmpty()) {
            return new Block();
        }

        return handleImport(block.get(), multipartFiles);
    }

    public Block handleImport(final Block block,
                              final List<MultipartFile> multipartFiles) throws IOException {
        if (!multipartFiles.isEmpty()) {
            for (final MultipartFile file : multipartFiles) {
                final var pages = getPages(block, file);
                block.addAllPages(pages);
            }
        } else {
            log.info("Não foi passado nenhum arquivo");
        }

        return save(block);
    }

    @Transactional
    public List<BlockPage> getPages(final Block block,
                                     final MultipartFile file) throws IOException {
        final var savedFile = fileUploadService.uploadFile(file);
        if (isPdf(file)) {
            final var pages = blockImporterService.importBlock(block, savedFile);
            if (!pages.isEmpty()) {
                return pages;
            }
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
