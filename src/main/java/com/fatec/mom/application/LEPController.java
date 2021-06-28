package com.fatec.mom.application;

import com.fatec.mom.domain.lep.LEP;
import com.fatec.mom.domain.lep.LEPService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/lep")
@Api(value = "List of Effective Pages (LEP) Controller")
public class LEPController {

    private final LEPService lepService;

    @Autowired
    public LEPController(LEPService lepService) {
        this.lepService = lepService;
    }

    @GetMapping("/data")
    @ApiOperation(value = "Retorna todas as revisões e as páginas da LEP")
    public ResponseEntity<LEP> getData(@RequestParam("document_id") final Long documentId) {
        final var lepData = lepService.getLEP(documentId);

        return ResponseEntity.ok(lepData);
    }

    @GetMapping("/generate")
    @ApiOperation(value = "Gera os dados da LEP de um documento")
    public ResponseEntity<LEP> generateData(@RequestParam("document_id") final Long documentId) {
        final var lep = lepService.compareChanges(documentId);

        return ResponseEntity.ok(lep);
    }

    @GetMapping("/download")
    @ApiOperation(value = "gera a versão Full")
    public ResponseEntity<InputStreamResource> downloadFullNew(
            @RequestParam("trait") Integer trait) throws IOException {
        System.out.println("aaaaaaa");
        //documentService é onde a lógica acontece
        lepService.downloadLep(trait);

        File currentDirFile = new File(".");
        String helper = currentDirFile.getAbsolutePath();
        String currentDir = helper.substring(0, helper.length() - 1);
        String rootPath = currentDir + "doc\\Mockup FATEC\\MOCKUP\\ABC-1234\\Master\\00 Inicial\\02 List of Effective Pages\\ABC-1234-00-02c0";

        if (trait == 50) {
            rootPath = rootPath + "1.pdf";
        }
        else if (trait == 55) {
            rootPath  = rootPath + "2.pdf";
        }
        else if (trait == 60) {
            rootPath  = rootPath + "3.pdf";
        }
        InputStreamResource resource = new InputStreamResource(new FileInputStream(rootPath));
        return ResponseEntity.ok()
                .contentLength(rootPath.length())
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }
}
