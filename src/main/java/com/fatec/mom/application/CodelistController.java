package com.fatec.mom.application;

import com.fatec.mom.domain.codelist.Codelist;
import com.fatec.mom.domain.codelist.CodelistConverterService;
//import com.fatec.mom.domain.codelist.CodelistService;
import com.fatec.mom.domain.document.Document;
import com.fatec.mom.domain.file.FileInfoService;
import com.fatec.mom.domain.file.FileUploadService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * A classe <code>CodelistController</code> é responsável por fornecer uma api onde é possível realizar a importação
 * de arquivos de codelist e transformá-los em objetos no banco de dados
 *
 * @author Tobias Lino
 * @version v01 26/03/2021
 */
@RestController
@RequestMapping("/codelist")
public class CodelistController {

    @Autowired
    private CodelistConverterService codelistConverterService;

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private FileInfoService fileInfoService;

//    @Autowired
//    private CodelistService codelistService;

    @PostMapping("/import")
    @ApiOperation(value = "Realiza a importação dos arquivos de codelist (que devem estar em formato Excel) " +
            "e salva os blocos/documentos de acordo com a especificação do codelist.",
            consumes = "Nome do documento (document_name), part number do documento (part_number) " +
                    "e arquivo codelist em formato excel.")
    public ResponseEntity<List<Document>> importCodelistAsExcel(
            @RequestParam("document_name") String documentName,
            @RequestParam("part_number") Integer partNumber,
            @RequestParam("file") MultipartFile file) throws IOException {

        fileUploadService.uploadFile(file);

        var doc = Document.builder()
                .name(documentName)
                .partNumber(partNumber)
                .createdDate(new Date())
                .build();

        var fileInfo = fileInfoService.build(file.getOriginalFilename());

        var savedDocs = codelistConverterService.convertFileDataIntoDocuments(doc, fileInfo);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(savedDocs);
    }

//    @GetMapping("/find/by")
//    @ApiOperation(value = "Retorna uma codelist completa com todos os documentos encontrados")
//    public ResponseEntity<Codelist> findCodelist(
//            @RequestParam("document_name") String documentName,
//            @RequestParam("part_number") Integer partNumber) {
//        var codelist = codelistService.findCodelist(documentName, partNumber);
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(codelist);
//    }
}
