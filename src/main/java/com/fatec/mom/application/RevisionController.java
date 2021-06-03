package com.fatec.mom.application;

import com.fatec.mom.domain.block.Block;
import com.fatec.mom.domain.block.BlockService;
import com.fatec.mom.domain.block.BlockStatus;
import com.fatec.mom.domain.revision.Revision;
import com.fatec.mom.domain.revision.RevisionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * A classe <code>RevisionController</code> é responsável por fornecer uma api para as revisões.
 *
 * @author Devanir
 * @version v01 07/05/2021
 */

@RestController
@RequestMapping("/revision")
@Api(value = "Revision Controller")
public class RevisionController {

    @Autowired
    private RevisionService revisionService;

    @Autowired
    private BlockService blockService;

    @PostMapping("/save")
    @ApiOperation(value = "Cria uma nova ou atualiza uma Revision no banco")
    public ResponseEntity<Revision> saveRev (
            @RequestBody Revision rev) {

        var newRev = revisionService.saveNewRevision(rev);

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
                .body(newRev);
    }

    @GetMapping("/opened")
    @ApiOperation(value = "Encontra uma revisão aberta")
    public ResponseEntity<Revision> hasOpenedRevision(@RequestParam("document_id") Long documentId) {
        var revision = revisionService.findOpened(documentId);

        return revision.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PutMapping("/close")
    @ApiOperation(value = "Fecha a revisão que está aberta")
    public ResponseEntity<Revision> closeRevision(@RequestParam("document_id") Long documentId) {
        var revision = revisionService.closeLastRevision(documentId);
        if (revision.isPresent()) {
            closeAllBlocks(revision.get().getBlocksInRevision());
            return ResponseEntity.ok(revision.get());
        }

        return ResponseEntity.notFound().build();
    }

    private void closeAllBlocks(List<Block> blocks) {
        if (blocks.isEmpty()) return;
        blocks.forEach(block -> {
            block.setStatus(BlockStatus.REVISED);
            blockService.save(block);
        });
    }

    @GetMapping("/find/blocks")
    @ApiOperation(value = "Obtém os blocos em revisão")
    public ResponseEntity<List<Block>> findBlocks(
            @RequestParam("doc_name") String documentName,
            @RequestParam("part_number") Integer partNumber) {
        var blocks = revisionService.findBlocks(documentName, partNumber);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(blocks);
    }
}
