//package com.fatec.mom.domain.codelist;
//
//import com.fatec.mom.domain.block.Block;
//import com.fatec.mom.domain.document.Document;
//import com.fatec.mom.domain.document.DocumentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//
//@Service
//public class CodelistService {
//
//    @Autowired
//    private DocumentService documentService;
//
//    private List<Integer> calculateChecklist(final List<Document> documents, final Block block) {
//        var checklist = new LinkedList<Integer>();
//        documents.forEach(document -> checklist.add(document.hasBlock(block) ? 1 : 0));
//
//        return checklist;
//    }
//
//    public Codelist findCodelist(String name, Integer partNumber) {
//        var docs = documentService.findAllByNameAndPartNumber(name, partNumber);
////        docs.sort(Comparator.comparing(Document::getTrait));
//
//        return Codelist.builder()
//                .documents(docs)
//                .codelistBlocks(getAllCodelistBlocks(getAllBlocks(docs), docs))
//                .build();
//    }
//
//    private List<Block> getAllBlocks(Document document) {
//        var blocks = new LinkedList<Block>();
//        blocks.addAll(document.getBlocks());
//        return blocks;
//    }
//
//    private Set<CodelistBlock> getAllCodelistBlocks(final List<Block> blocks, final List<Document> documents) {
//        var codelistBlocks = new HashSet<CodelistBlock>();
//        blocks.forEach(block -> codelistBlocks.add(CodelistBlock.createCodelistBlock(block)));
//        codelistBlocks.forEach(codelistBlock -> codelistBlock.setChecklist(calculateChecklist(documents, codelistBlock.getBlock())));
//        return codelistBlocks;
//    }
//}
