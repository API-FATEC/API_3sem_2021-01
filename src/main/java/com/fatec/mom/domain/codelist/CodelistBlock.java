package com.fatec.mom.domain.codelist;

import com.fatec.mom.domain.block.Block;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
@AllArgsConstructor
public class CodelistBlock {

    private Block block;
    private List<Integer> checklist;

    public static CodelistBlock createCodelistBlock(Block block) {
        return new CodelistBlock(block, new LinkedList<>());
    }
}
