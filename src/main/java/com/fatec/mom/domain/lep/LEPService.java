package com.fatec.mom.domain.lep;

import com.fatec.mom.domain.block.*;
import com.fatec.mom.domain.block.pagescomparator.changes.BlockPageChange;
import com.fatec.mom.domain.document.Document;
import com.fatec.mom.domain.document.DocumentService;
import com.fatec.mom.domain.revision.Revision;
import com.fatec.mom.domain.revision.RevisionService;
import com.fatec.mom.domain.trait.Trait;
import com.fatec.mom.infra.exceptions.CannotFindBlockFileException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LEPService {

    private final BlockService blockService;

    public List<InputStream> getFilesByTrait(final Document document, final Trait trait) {
        final var blocksByTrait = blockService.getBlocksByTrait(document, trait);
        if (blocksByTrait.isEmpty()) {
            return Collections.emptyList();
        }
        return getInputStreamsFromBlocks(blocksByTrait);
    }

    private List<InputStream> getInputStreamsFromBlocks(final Set<Block> blocks) {
        final var files = blockService.getFileNamesFrom(blocks);
        return getInputStreamsFrom(files);
    }

    private List<InputStream> getInputStreamsFrom(final List<String> files) {
        if (files.isEmpty()) return Collections.emptyList();

        return files.stream().map(fileName -> {
            try {
                return new FileInputStream(fileName);
            } catch (FileNotFoundException fileNotFoundException) {
                throw new CannotFindBlockFileException("Não foi possível encontrar os arquivos", fileNotFoundException);
            }
        }).collect(Collectors.toUnmodifiableList());
    }
}
