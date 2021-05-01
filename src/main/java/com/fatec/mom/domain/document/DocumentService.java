package com.fatec.mom.domain.document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Transactional
    public List<Document> findAll() {
        return documentRepository.findAll();
    }

    @Transactional
    public Set<Document> findAllByNameAndPartNumber(final String name, final Integer partNumber) {
        final var docs = documentRepository.findAll(DocumentSpecification.searchByName(name));
        return docs.stream()
                .filter(document -> document.getPartNumber().equals(partNumber))
                .collect(Collectors.toSet());
    }

    @Transactional
    public Set<Document> findAllByNameAndPartNumberAndTrait(final String name, final Integer partNumber, Integer trait) {
        final var docs = documentRepository.findAll(DocumentSpecification.searchByName(name));
        return docs.stream()
                .filter(document -> document.getPartNumber().equals(partNumber))
                .filter(document -> document.containsTrait(trait))
                .collect(Collectors.toSet());
    }

    @Transactional
    public List<Document> saveAll(final List<Document> documents) {
        return documentRepository.saveAll(documents);
    }

    @Transactional
    public Set<Integer> findAllPartNumbersByName(final String name) {
        var docs = documentRepository.findAll(DocumentSpecification.searchByName(name));
        return docs.stream().map(Document::getPartNumber).collect(Collectors.toSet());
    }

    @Transactional
    public Set<String> findAllNames() {
        var docs = documentRepository.findAll();
        return docs.stream().map(Document::getName).collect(Collectors.toSet());
    }
}
