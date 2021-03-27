package com.fatec.mom.domain.block;

import com.fatec.mom.domain.utils.ModelDeserializer;

import java.util.List;

public class BlockDeserializer implements ModelDeserializer<Block> {

    @Override
    public List<Block> deserializeAll(List<String> rowCells) {
        return null;
    }

    @Override
    public Block deserialize(List<String> rowCells, int order) {
        final String section = rowCells.get(0);
        String subSection = "";

        if (rowCells.get(1).equalsIgnoreCase("NULL")) {
            subSection = rowCells.get(1);
        }
        final String number = rowCells.get(2);
        final String name = rowCells.get(3);
        final String code = rowCells.get(4);

        return Block.builder()
                .section(section)
                .subSection(subSection)
                .number(Integer.parseInt(number))
                .name(name)
                .code(Integer.parseInt(code))
                .order(order)
                .build();
    }
}
