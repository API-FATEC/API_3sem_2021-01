package com.fatec.mom.domain.block.validator.validators;

import com.fatec.mom.domain.block.Block;
import com.fatec.mom.infra.exceptions.InvalidBlockOrderException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public abstract class BlockOrderValidator {

    @Getter(AccessLevel.PROTECTED)
    private final Integer order;

    public abstract void validate(final List<Block> blocks) throws InvalidBlockOrderException;
}
