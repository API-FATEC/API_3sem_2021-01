package com.fatec.mom.infra.config;

import com.fatec.mom.domain.codelist.CodelistConverterChain;
import com.fatec.mom.domain.codelist.converter.filters.AbstractConverterFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * A classe <code>ConverterChainConfig</code> configura os filtros que farão parte da corrente de conversores de documentos.
 *
 * @author Tobias Lino
 * @version v01 26/03/2021
 */
@Configuration
public class ConverterChainConfig {

    @Autowired
    @Qualifier("documentConverterFilter")
    private AbstractConverterFilter documentConverterFilter;

    @Autowired
    @Qualifier("blockConverterFilter")
    private AbstractConverterFilter blockConverterFilter;

    @Autowired
    @Qualifier("documentPersisterFilter")
    private AbstractConverterFilter documentPersisterFilter;

    @Autowired
    private CodelistConverterChain codelistConverterChain;

    @Bean
    public void configureConverterChain() {
        documentConverterFilter
                .linkWith(blockConverterFilter)
                .linkWith(documentPersisterFilter);

        codelistConverterChain.setConverterFilter(documentConverterFilter);
    }
}
