package com.fatec.mom.domain.block.validator.validators;

import com.fatec.mom.domain.block.Block;
import com.fatec.mom.infra.exceptions.InvalidBlockOrderException;

import java.util.List;
import java.util.Optional;

public class ValidateLetter extends BlockOrderValidator {

    private static final String LETTER = "letter";

    public ValidateLetter(Integer order) {
        super(order);
    }

    @Override
    public void validate(List<Block> blocks) throws InvalidBlockOrderException {
        final var block = Optional.ofNullable(blocks.get(getOrder()));

        if (block.isPresent()) {
            if (!block.get().getName().equalsIgnoreCase(LETTER)) throw new InvalidBlockOrderException("A ordem não está correta", blocks);
        }
    }
}
