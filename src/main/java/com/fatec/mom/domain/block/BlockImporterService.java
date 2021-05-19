package com.fatec.mom.domain.block;

import com.fatec.mom.infra.file.extractor.FileExtractor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class BlockImporterService {

    private final FileExtractor fileExtractor;

    @Autowired
    public BlockImporterService(FileExtractor fileExtractor) {
        this.fileExtractor = fileExtractor;
    }

    public List<BlockPage> importBlock(final Block block, final File file) throws FileNotFoundException {
        final var pages = getPages(block, file);

        if (pages.isEmpty()) {
            log.info("Nenhuma página encontrada");
            return Collections.emptyList();
        }

        return pages;
    }

    private List<BlockPage> getPages(final Block block, final File file) throws FileNotFoundException {
        final var inputStream = new FileInputStream(file);
        return fileExtractor.extractPages(block, inputStream);
    }
}
