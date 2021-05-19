package com.fatec.mom.infra.generator.full.dataextractor;

import com.fatec.mom.domain.document.Document;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class DocumentFilesExtractor {

    public List<String> extractFileNames(final Document document) {
        final var fileNames = new LinkedList<String>();
        final var blocks = document.getBlocks();

        blocks.forEach(block -> fileNames.add(block.getBasePath()));

        return fileNames;
    }
}
