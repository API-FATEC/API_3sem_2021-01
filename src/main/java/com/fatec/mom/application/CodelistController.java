package com.fatec.mom.application;

import com.fatec.mom.domain.codelist.CodelistConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/codelis")
public class CodelistController {

    @Autowired
    private CodelistConverterService codelistConverterService;

    @PostMapping("/import")
    public ResponseEntity<String> importCodelistAsExcel(
            @RequestParam("document_name") String documentName,
            @RequestParam("part_number") Integer partNumber,
            @RequestParam("file") MultipartFile file) {

        return ResponseEntity.ok()
                .body("");
    }
}
