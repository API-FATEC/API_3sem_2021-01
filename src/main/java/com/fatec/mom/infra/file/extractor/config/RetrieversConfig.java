package com.fatec.mom.infra.file.extractor.config;

import com.fatec.mom.infra.file.extractor.retrievers.DataRetriever;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedList;
import java.util.List;

@Configuration
public class RetrieversConfig {

    @Bean
    public List<DataRetriever> dataRetrievers(
            @Qualifier("revisionRetriever") DataRetriever revisionRetriever,
            @Qualifier("pageRetriever") DataRetriever pageRetriever,
            @Qualifier("codeRetriever") DataRetriever codeRetriever) {
        final List<DataRetriever> retrievers = new LinkedList<>();
        retrievers.add(revisionRetriever);
        retrievers.add(pageRetriever);
//        retrievers.add(codeRetriever);
        return retrievers;
    }
}
