package com.fatec.mom.domain.block.validator.validators;

import com.fatec.mom.domain.block.Block;
import com.fatec.mom.infra.exceptions.InvalidBlockOrderException;

import java.util.List;
import java.util.Optional;

public class ValidateTableOfContents extends BlockOrderValidator {

    private static final String TABLE_OF_CONTENTS = "toc";

    public ValidateTableOfContents(Integer order) {
        super(order);
    }

    @Override
    public void validate(List<Block> blocks) throws InvalidBlockOrderException {
        final var block = Optional.ofNullable(blocks.get(getOrder()));

        if (block.isPresent()) {
            if (!block.get().getName().equalsIgnoreCase(TABLE_OF_CONTENTS)) throw new InvalidBlockOrderException("A ordem não está correta", blocks);
        }
    }
}
