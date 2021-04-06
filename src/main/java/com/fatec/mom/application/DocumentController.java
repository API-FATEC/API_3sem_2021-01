package com.fatec.mom.application;

import com.fatec.mom.domain.document.Document;
import com.fatec.mom.domain.document.DocumentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<Document>> findDocuments(
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
    public ResponseEntity<List<Document>> findAllByNameAndPartNumber(
            @RequestParam("document_name") String documentName,
            @RequestParam("part_number") Integer partNumber) {
        var docs = documentService.findAllByNameAndPartNumber(documentName, partNumber);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(docs);
    }
}
