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
    public Document findByNameAndPartNumberAndTrait(String name, Integer partNumber, Integer trait) {
        return documentRepository.findByNameAndPartNumberAndTrait(name, partNumber, trait).orElse(null);
    }

    @Transactional
    public List<Document> findAllByNameAndPartNumber(final String name, final Integer partNumber) {
        return documentRepository.findAllByNameAndPartNumber(name, partNumber);
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
