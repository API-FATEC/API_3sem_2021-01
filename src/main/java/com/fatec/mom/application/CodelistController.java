package com.fatec.mom.application;

import com.fatec.mom.domain.codelist.CodelistConverterService;
import com.fatec.mom.domain.document.Document;
import com.fatec.mom.domain.file.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/codelist")
public class CodelistController {

    @Autowired
    private CodelistConverterService codelistConverterService;

    @Value("default-file-upload-path")
    private String defaultUploadPath;

    @PostMapping("/import")
    public ResponseEntity<String> importCodelistAsExcel(
            @RequestParam("document_name") String documentName,
            @RequestParam("part_number") Integer partNumber,
            @RequestParam("file") MultipartFile file) throws IOException {

        var doc = Document.builder()
                .name(documentName)
                .partNumber(partNumber).build();

        var fileInfo = FileInfo.builder()
                .fileName(file.getName())
                .actualIndex(1)
                .totalRows(27)
                .build();

        codelistConverterService.convertFileDataIntoDocuments(doc, fileInfo);

        return ResponseEntity.ok()
                .body("");
    }
}
