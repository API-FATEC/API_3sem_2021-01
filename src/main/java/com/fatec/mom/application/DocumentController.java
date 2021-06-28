package com.fatec.mom.application;

import com.fatec.mom.domain.document.Document;
import com.fatec.mom.domain.document.DocumentService;
import com.fatec.mom.domain.revision.Revision;
import com.fatec.mom.domain.revision.RevisionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
@Api(value = "Document Controller")
public class DocumentController {

    private final DocumentService documentService;

    private final RevisionService revisionService;

    @Autowired
    public DocumentController(DocumentService documentService, RevisionService revisionService) {
        this.documentService = documentService;
        this.revisionService = revisionService;
    }

    @GetMapping("/find/all")
    @ApiOperation(value = "Obtém todos os documentos salvos")
    public ResponseEntity<List<Document>> findAllDocuments() {
        var docs = documentService.findAll();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(docs);
    }

    @GetMapping("/find")
    @ApiOperation(value = "Obtém um documento específico de acordo com nome, partNumber e traço")
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
    @ApiOperation(value = "Obtém todos os partNumber passando o nome do documento.")
    public ResponseEntity<Set<Integer>> findAllPartNumberByDocumentName(
            @RequestParam("document_name") String documentName) {
        var docs = documentService.findAllPartNumbersByName(documentName);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(docs);
    }

    @GetMapping("find/name/all")
    @ApiOperation(value = "Obtém todos os nomes dos documentos ativos")
    public ResponseEntity<Set<String>> findAllNames() {
        var names = documentService.findAllNames();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(names);
    }

    @GetMapping("/download/full/{revision_id}")
    @ApiOperation(value = "Retorna o full do documento, especificando a revisão")
    public ResponseEntity<InputStreamResource> downloadFull(@PathVariable("revision_id") Long revisionId) {
        final var revision = revisionService.findById(revisionId);
        final var resource = documentService.generateFULL(revision);

        if (resource.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource.get());
    }

    @PostMapping("/import/{id}")
    @ApiOperation(value = "Cria um documento importando os arquivos")
    public ResponseEntity<Document> importDocument(@PathVariable("id") final Long id) {
        final var document = documentService.importDocument(id);

        return ResponseEntity.ok(document);
    }

    @GetMapping("/download/full")
    @ApiOperation(value = "gera a versão Full")
    public ResponseEntity<InputStreamResource> downloadFullNew(
            @RequestParam("trait") String trait) throws IOException {
        //documentService é onde a lógica acontece
        documentService.downloadFullNew(trait);

        File desktop = new File(System.getProperty("user.home"), "Desktop");
        File pdf = new File(desktop.getPath() + "\\Desktop\\[]ABC-1234-" + trait + "-FULL.pdf");
        InputStreamResource resource = new InputStreamResource(new FileInputStream(pdf));
        return ResponseEntity.ok()
                .contentLength(pdf.length())
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    @PostMapping("/save")
    @ApiOperation(value = "Salva um document no banco")
    public  ResponseEntity<Document> saveDoc (
            @RequestBody final Document doc) {

        var newDoc = documentService.save(doc);

        return ResponseEntity.ok(doc);
    }
}
