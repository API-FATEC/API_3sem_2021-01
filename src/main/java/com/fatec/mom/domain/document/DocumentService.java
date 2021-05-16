package com.fatec.mom.domain.document;

import com.fatec.mom.domain.revision.RevisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private RevisionService revisionService;

    @Transactional
    public List<Document> findAll() {
        return documentRepository.findAll();
    }

    @Transactional
    public Document findByNameAndPartNumber(final String name, final Integer partNumber) {
        final var docs = documentRepository.findAll(DocumentSpecification.searchByName(name));
        return docs.stream()
                .filter(document -> document.getPartNumber().equals(partNumber))
                .findFirst().orElseThrow();
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
        return documents.stream().map(document -> documentRepository.save(document)).collect(Collectors.toList());
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

    @Transactional
    public Document save(Document document) {
        return documentRepository.save(document);
    }

    @Transactional
    public Optional<Document> findById(final Long id) {
        return documentRepository.findById(id);
    }
}
