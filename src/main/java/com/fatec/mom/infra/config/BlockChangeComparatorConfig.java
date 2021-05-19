package com.fatec.mom.infra.config;

import com.fatec.mom.domain.block.pagescomparator.changes.ChangeHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedList;
import java.util.List;

@Configuration
public class BlockChangeComparatorConfig {

    @Bean
    public List<ChangeHandler> changeHandlers(@Qualifier("deletedPageHandler") ChangeHandler deletedPageHandler,
                                              @Qualifier("newPageHandler") ChangeHandler newPageHandler) {
        List<ChangeHandler> changeHandlers = new LinkedList<>();
//        changeHandlers.add(deletedPageHandler);
//        changeHandlers.add(newPageHandler);
        return changeHandlers;
    }
}
