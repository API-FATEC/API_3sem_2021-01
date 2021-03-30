package com.fatec.mom.application;

import com.fatec.mom.domain.codelist.CodelistConverterService;
import com.fatec.mom.domain.document.Document;
import com.fatec.mom.domain.document.DocumentService;
import com.fatec.mom.domain.file.FileInfo;
import com.fatec.mom.domain.file.FileInfoService;
import com.fatec.mom.domain.file.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/codelist")
public class CodelistController {

    @Autowired
    private CodelistConverterService codelistConverterService;

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private FileInfoService fileInfoService;

    @PostMapping("/import")
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
}
