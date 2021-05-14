package com.fatec.mom.domain.codelist;

import com.fatec.mom.domain.block.Block;
import com.fatec.mom.domain.document.Document;
import com.fatec.mom.domain.document.DocumentService;
import com.fatec.mom.domain.trait.Trait;
import com.fatec.mom.infra.codelist.reader.CodelistReaderType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.*;

@Service
public class CodelistService {

    private final DocumentService documentService;

    private final CodelistImporterService codelistImporterService;

    @Autowired
    public CodelistService(DocumentService documentService,
                           CodelistImporterService codelistImporterService) {
        this.documentService = documentService;
        this.codelistImporterService = codelistImporterService;
    }

    public List<Document> importCodelist(@NotNull final MultipartFile file) {
        final List<Document> documents = codelistImporterService.read(CodelistReaderType.MULTIPLE_TAB, file);

        if (documents != null) {
            return documentService.saveAll(documents);
        }
        return Collections.emptyList();
    }

    private List<Integer> calculateChecklist(final Set<Trait> traits, final Block block) {
        var checklist = new LinkedList<Integer>();
        traits.forEach(trait -> checklist.add(block.hasTrait(trait) ? 1 : 0));
        return checklist;
    }

    public Codelist findCodelist(String name, Integer partNumber) {
        final Document doc = documentService.findByNameAndPartNumber(name, partNumber);

        return Codelist.builder()
                .document(doc)
                .codelistBlocks(getAllCodelistBlocks(doc.getBlocks(), doc.getTraits()))
                .build();
    }

    private Set<CodelistBlock> getAllCodelistBlocks(final Set<Block> blocks, final Set<Trait> traits) {
        var codelistBlocks = new HashSet<CodelistBlock>();
        blocks.forEach(block -> codelistBlocks.add(CodelistBlock.createCodelistBlock(block)));
        codelistBlocks.forEach(codelistBlock -> codelistBlock.setChecklist(calculateChecklist(traits, codelistBlock.getBlock())));
        return codelistBlocks;
    }
}
