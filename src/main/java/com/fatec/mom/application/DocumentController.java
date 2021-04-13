package com.fatec.mom.application;

import com.fatec.mom.domain.document.Document;
import com.fatec.mom.domain.document.DocumentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Document> findDocuments(
            @RequestParam("document_name") String documentName,
            @RequestParam("part_number") Integer partNumber,
            @RequestParam("trait") Integer trait) {
        var doc = documentService.findByNameAndPartNumberAndTrait(documentName, partNumber, trait);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(doc);
    }

    @GetMapping("/find/all/by")
    @ApiOperation(value = "Retorna todos os documentos e traços de acordo com o nome e partnumber")
    public ResponseEntity<List<Document>> findAllByNameAndPartNumber(
            @RequestParam("document_name") String documentName,
            @RequestParam("part_number") Integer partNumber) {
        var docs = documentService.findAllByNameAndPartNumber(documentName, partNumber);

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

    @PutMapping("/update/add/block")
    @ApiOperation(value = "Adiciona um bloco novo a um documento existente")
    public ResponseEntity<Document> updateAddBlock(
            @RequestParam("block_secao") String secao,
            @RequestParam("block_sub_secao") String subSecao,
            @RequestParam("block_numero") int numero,
            @RequestParam("block_nome") String nome,
            @RequestParam("block_codigo") int codigo,
            @RequestParam("block_order") int order,
            @RequestBody Document doc){
        var document = documentService.addBlock(doc, secao, subSecao, numero, nome, codigo, order);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(document);
    }
}
