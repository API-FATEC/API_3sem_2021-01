package com.fatec.mom.infra.exceptions;

import com.fatec.mom.domain.block.Block;
import lombok.Getter;

import java.util.List;

public class InvalidBlockOrderException extends RuntimeException {

    @Getter
    private final List<Block> actualOrder;

    public InvalidBlockOrderException(String message, final List<Block> order) {
        super(message);
        this.actualOrder = order;
    }
}
