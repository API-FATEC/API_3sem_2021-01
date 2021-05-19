package com.fatec.mom.application;

import com.fatec.mom.domain.lep.LEP;
import com.fatec.mom.domain.lep.LEPService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
