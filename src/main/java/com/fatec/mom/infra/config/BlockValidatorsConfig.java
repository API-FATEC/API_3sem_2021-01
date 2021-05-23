package com.fatec.mom.infra.config;

import com.fatec.mom.domain.block.validator.validators.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Configuration
public class BlockValidatorsConfig {

    @Bean
    public List<BlockOrderValidator> validators() {
        return new LinkedList<>() {{
            add(new ValidateLetter(blocksOrder().get(DefaultBlockNames.LETTER)));
            add(new ValidateCover(blocksOrder().get(DefaultBlockNames.COVER)));
            add(new ValidateLEP(blocksOrder().get(DefaultBlockNames.LEP)));
            add(new ValidateTableOfContents(blocksOrder().get(DefaultBlockNames.TABLE_OF_CONTENTS)));
        }};
    }

    @Bean
    public Map<DefaultBlockNames, Integer> blocksOrder() {
        return new EnumMap<>(DefaultBlockNames.class) {{
            put(DefaultBlockNames.LETTER, 1);
            put(DefaultBlockNames.COVER, 2);
            put(DefaultBlockNames.LEP, 3);
            put(DefaultBlockNames.TABLE_OF_CONTENTS, 4);
        }};
    }
}
