package com.fatec.mom.domain.block;

import com.fatec.mom.domain.utils.ConverterDeserializerWithOrder;

import java.util.List;

/**
 * A classe <code>BlockDeserializer</code> é responsável por transformar os valores das células do arquivo de codelist
 * em objetos do tipo <code>Block</code>.
 *
 * @author Tobias Lino
 * @version v01 26/03/2021
 */
public class BlockDeserializer implements ConverterDeserializerWithOrder<Block> {

    @Override
    public Block deserialize(List<String> rowCells, int order) {
        final String section = rowCells.get(0);
        String subSection = "";

        if (!rowCells.get(1).equalsIgnoreCase("NULL")) {
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
