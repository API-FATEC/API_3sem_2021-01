package com.fatec.mom.infra.generator.full.dataextractor;

import com.fatec.mom.domain.block.Block;
import com.fatec.mom.domain.block.BlockService;
import com.fatec.mom.domain.block.validator.BlockOrderValidatorLocator;
import com.fatec.mom.domain.document.Document;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DocumentFilesExtractor {
    /*
    private final BlockService blockService;

    public DocumentFilesExtractor(BlockService blockService) {
        this.blockService = blockService;
    }

    public Map<Integer, List<String>> extractFileNames(final Document document) {
        if (document.getTraits() == null || document.getTraits().isEmpty()) {
            throw new RuntimeException(String.format("Documento %s não possui traços", document.getDocument()));
        }

        final var fileNames = new HashMap<Integer, List<String>>();

        document.getTraits().forEach(trait -> {
            fileNames.put(trait.getNumber(), getFileNames(trait.getNumber(), document.getId()));
        });

        return fileNames;
    }

    private List<String> getFileNames(final Integer trait, final Long documentId) {
        final var fileNames = new LinkedList<String>();

        final var blocks = getBlocksFromTrait(trait, documentId);
        blocks.forEach(block -> fileNames.add(block.getBasePath()));

        return fileNames;

    }

    private List<Block> getBlocksFromTrait(final Integer trait, final Long documentId) {
        final var blocks = blockService.getAllBlocksFrom(trait, documentId);
        blockService.validateBlocksOrder(blocks);
        return blocks;
    }
    */
}
