package com.fatec.mom.domain.codelist;

import com.fatec.mom.domain.block.Block;
import com.fatec.mom.domain.document.Document;
import com.fatec.mom.domain.document.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CodelistService {

    @Autowired
    private DocumentService documentService;

    private List<Integer> calculateChecklist(final List<Document> documents, final Block block) {
        var checklist = new LinkedList<Integer>();
        documents.forEach(document -> checklist.add(document.hasBlock(block) ? 1 : 0));

        return checklist;
    }

    public Codelist findCodelist(String name, Integer partNumber) {
        var docs = documentService.findAllByNameAndPartNumber(name, partNumber);
        docs.sort(Comparator.comparing(Document::getTrait));

        return Codelist.builder()
                .documents(docs)
                .codelistBlocks(getAllCodelistBlocks(getAllBlocks(docs), docs))
                .build();
    }

    private List<Block> getAllBlocks(List<Document> documents) {
        var blocks = new LinkedList<Block>();
        documents.forEach(document -> blocks.addAll(document.getBlocks()));
        return blocks;
    }

    private Set<CodelistBlock> getAllCodelistBlocks(final List<Block> blocks, final List<Document> documents) {
        var codelistBlocks = new HashSet<CodelistBlock>();
        blocks.forEach(block -> codelistBlocks.add(CodelistBlock.createCodelistBlock(block)));
        codelistBlocks.forEach(codelistBlock -> codelistBlock.setChecklist(calculateChecklist(documents, codelistBlock.getBlock())));
        return codelistBlocks;
    }

    public List<Document> saveCodelist(final Codelist codelist, final String documentName, final Integer partNumber, final List<String> traits) {
        var doc = Document.builder()
                .name(documentName)
                .partNumber(partNumber)
                .build();
        var docs = generateAllDocuments(doc, traits);
        var codelistBlocks = codelist.getCodelistBlocks();
        var populatedDocs = insertBlockFromCodelist(codelistBlocks, docs);

        return documentService.saveAll(populatedDocs);
    }

    private List<Document> generateAllDocuments(final Document defaultDocument, final List<String> traits) {
        var docs = new LinkedList<Document>();
        traits.stream().mapToInt(trait -> Integer.parseInt(trait.substring(trait.indexOf("_") + 1))).forEach(value -> {
            var document = Document.builder()
                    .name(defaultDocument.getName())
                    .partNumber(defaultDocument.getPartNumber())
                    .trait(value)
                    .blocks(new HashSet<>())
                    .build();
            docs.add(document);
        });
        return docs;
    }

    private List<Document> insertBlockFromCodelist(final Set<CodelistBlock> codelistBlocks, final List<Document> documents) {
        var docs = new LinkedList<>(documents);

        codelistBlocks.forEach(block -> {
            block.getChecklist().forEach(item -> {
                if (item == 1)
                    docs.get(item).addBlock(block.getBlock());
            });
        });

        return docs;
    }
}
