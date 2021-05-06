package com.fatec.mom.application;

import com.fatec.mom.domain.document.Document;
import com.fatec.mom.domain.document.DocumentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * A classe <code>DocumentController</code> é responsável por fornecer uma api para realização de buscas em documentos
 * específicos.
 *
 * @author Tobias Lino
 * @version v01 26/03/2021
 */
@RestController
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @GetMapping("/find/all")
    public ResponseEntity<List<Document>> findAllDocuments() {
        var docs = documentService.findAll();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(docs);
    }

    @GetMapping("/find")
    public ResponseEntity<Set<Document>> findDocuments(
            @RequestParam("document_name") String documentName,
            @RequestParam("part_number") Integer partNumber,
            @RequestParam("trait") Integer trait) {
        var docs = documentService.findAllByNameAndPartNumberAndTrait(documentName, partNumber, trait);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(docs);
    }

    @GetMapping("/find/all/by")
    @ApiOperation(value = "Retorna todos os documentos e traços de acordo com o nome e partnumber")
    public ResponseEntity<Document> findAllByNameAndPartNumber(
            @RequestParam("document_name") String documentName,
            @RequestParam("part_number") Integer partNumber) {
        var docs = documentService.findByNameAndPartNumber(documentName, partNumber);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(docs);
    }

    @PutMapping("/update/all")
    @ApiOperation(value = "Atualiza todos os documentos de uma lista, bem como todos os seus blocos")
    public ResponseEntity<List<Document>> updateAllDocs(@RequestBody final List<Document> documents) {
        var docs = documentService.saveAll(documents);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(docs);
    }

    @GetMapping("/find/part_number/by/name")
    public ResponseEntity<Set<Integer>> findAllPartNumberByDocumentName(
            @RequestParam("document_name") String documentName) {
        var docs = documentService.findAllPartNumbersByName(documentName);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(docs);
    }

    @GetMapping("find/name/all")
    public ResponseEntity<Set<String>> findAllNames() {
        var names = documentService.findAllNames();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(names);
    }
}
