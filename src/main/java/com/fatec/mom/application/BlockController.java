package com.fatec.mom.application;

import com.fatec.mom.domain.block.Block;
import com.fatec.mom.domain.block.BlockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/block")
@Api(value = "Block Controller")
public class BlockController {

    private final BlockService blockService;

    @Autowired
    public BlockController(BlockService blockService) {
        this.blockService = blockService;
    }

    @PostMapping("/import")
    @ApiOperation(value = "Salva um novo bloco, seus arquivos (pdf ou docx) e extrai as páginas do pdf, se existir.")
    public ResponseEntity<Block> importBlock(@RequestParam("block_id") Long blockId,
                                             @RequestParam("files") List<MultipartFile> files) throws IOException {
        final var savedBlock = blockService.handleImport(blockId, files);

        return ResponseEntity.ok(savedBlock);
    }
}
