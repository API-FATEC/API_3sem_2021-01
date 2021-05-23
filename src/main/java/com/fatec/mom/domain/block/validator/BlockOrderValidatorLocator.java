package com.fatec.mom.domain.block.validator;

import com.fatec.mom.domain.block.validator.validators.BlockOrderValidator;
import com.fatec.mom.domain.block.validator.validators.DefaultBlockNames;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.List;

@Component
@Getter
public class BlockOrderValidatorLocator {

    private final Map<DefaultBlockNames, Integer> defaultOrder;

    private final List<BlockOrderValidator> validators;

    public BlockOrderValidatorLocator(@Qualifier("blocksOrder") Map<DefaultBlockNames, Integer> defaultOrder,
                                      @Qualifier("validators") List<BlockOrderValidator> validators) {
        this.defaultOrder = defaultOrder;
        this.validators = validators;
    }
}
